package com.ssafy.ssafit.model.dao;

import java.util.Optional;

import com.ssafy.ssafit.model.dto.FollowDTO;

public interface FollowDao {
	public void insert(FollowDTO followDTO);
	public void delete(FollowDTO followDTO);
	public Optional<FollowDTO> selectByFollowIdAndUserId(FollowDTO followDTO);
}
