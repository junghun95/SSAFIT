package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.LikeDTO;

public interface LikeDao {
	void insert(LikeDTO likeDTO);
	List<LikeDTO> selectByUserId(int userId);
	void delete(LikeDTO likeDTO);
}
