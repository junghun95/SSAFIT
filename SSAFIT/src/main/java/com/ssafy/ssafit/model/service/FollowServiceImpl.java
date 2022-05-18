package com.ssafy.ssafit.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.exception.AlreadyExistFollowRel;
import com.ssafy.ssafit.exception.NotExistFollowRel;
import com.ssafy.ssafit.model.dao.FollowDao;
import com.ssafy.ssafit.model.dto.FollowDTO;

import lombok.RequiredArgsConstructor;

@Service("followService")
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{
	private final FollowDao followDao;

	@Override
	public void addFollow(FollowDTO followDTO) {
		if(findOne(followDTO) != null) throw new AlreadyExistFollowRel("이미 존재하는 팔로우 관계 입니다.");
		followDao.insert(followDTO);
	}

	@Override
	public void removeFollow(FollowDTO followDTO) {
		if(findOne(followDTO) == null) throw new NotExistFollowRel("존재하지 않는 팔로우 관계 입니다.");		
		followDao.delete(followDTO);
	}
	
	
	private FollowDTO findOne(FollowDTO followDTO) {
		return followDao.selectByFollowIdAndUserId(followDTO).orElseGet(null);
	}
	
}
