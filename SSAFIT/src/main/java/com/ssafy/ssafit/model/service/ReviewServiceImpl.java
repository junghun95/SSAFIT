package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.ReviewDTO;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<ReviewDTO> getVideoReviewList(String id) {
		return reviewDao.selectVideoReview(id);
	}

	@Override
	public List<ReviewDTO> getBoardReviewList(int id) {
		return reviewDao.selectBoardReview(id);
	}
}
