package com.ssafy.ssafit.model.dao;


import java.util.List;

import com.ssafy.ssafit.model.dto.ReviewDTO;

public interface ReviewDao {
	void insert(ReviewDTO review);
	void delete(int id);
	void update(ReviewDTO review);
	List<ReviewDTO> selectVideoReview(String id);
	List<ReviewDTO> selectBoardReview(int id);
}
