package com.ssafy.ssafit.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.model.dao.ReportDao;
import com.ssafy.ssafit.model.dto.BoardDTO;
import com.ssafy.ssafit.model.dto.ReportDTO;
import com.ssafy.ssafit.model.dto.ReviewDTO;
import com.ssafy.ssafit.util.RedisUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {
	
	private static final Logger log = LoggerFactory.getLogger(ReportService.class);

	private final ReportDao reportDao;
	private final RedisUtil redisUtil;
	private final BoardService boardService;
	private final ReviewService reviewService;
	private static final long LIMIT = 1000L*60*60*24*7;
	private static final long MAX = 10L;
	public void report(ReportDTO reportDTO) {
		reportDao.insert(reportDTO);
		
		String type = reportDTO.getReviewId() == 0 ? "board" : "review";
		int id =  reportDTO.getReviewId() == 0 ? reportDTO.getBoardId() : reportDTO.getReviewId();
		
		String key = type.equals("r") ? type+String.valueOf(id) :
													type+String.valueOf(id);
		Integer cnt = redisUtil.get(key) == null? 1 : (Integer)redisUtil.get(key) + 1;
		log.info("type: {}, id: {}, key: {}, cnt: {}",type,key, id, cnt);
		
		if(cnt == MAX) {
			if(type.equals("r")) {
				ReviewDTO review = reviewService.getReview(id);
				review.setIsDeleteNY("Y");
				reviewService.modifyReview(review);
			}
			else {
				BoardDTO board = boardService.getOneById(id);
				board.setIsDeleteNY("Y");
				boardService.modify(board);
			}
			return;
		}
		redisUtil.set(key, cnt, LIMIT);
	}
	
}
