package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.FollowDTO;

public interface FollowService {
	public void addFollow(FollowDTO followDTO);
	public void removeFollow(FollowDTO followDTO);
}
