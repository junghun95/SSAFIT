package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.ReviewDTO;

public interface ReviewService {

	List<ReviewDTO> getVideoReviewList(String id);

	List<ReviewDTO> getBoardReviewList(int id);


	void writeReview(ReviewDTO review);

	void removeReview(int id);

	void modifyReview(ReviewDTO review);

}
