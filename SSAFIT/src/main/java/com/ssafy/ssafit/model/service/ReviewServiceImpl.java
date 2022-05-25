package com.ssafy.ssafit.model.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.ReviewDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

	private final ReviewDao reviewDao;
	
	@Override
	public List<ReviewDTO> getVideoReviewList(String id) {
		return reviewDao.selectVideoReview(id);
	}

	@Override
	public List<ReviewDTO> getBoardReviewList(int id) {
		return reviewDao.selectBoardReview(id);
	}

	@Override
	public void writeReview(ReviewDTO review) {
		reviewDao.insert(review);
	}

	@Override
	public void removeReview(int id) {
		reviewDao.delete(id);
	}

	@Override
	public void modifyReview(ReviewDTO review) {
		reviewDao.update(review);
	}

	@Override
	public ReviewDTO getReview(int id) {
		return reviewDao.selectReview(id);
	}

}
