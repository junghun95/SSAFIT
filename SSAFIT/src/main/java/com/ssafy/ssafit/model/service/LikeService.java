package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.LikeDao;
import com.ssafy.ssafit.model.dto.LikeDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final LikeDao likeDao;
	
	public void doLike(LikeDTO likeDTO) {
		likeDao.insert(likeDTO);
	}
	
	public List<LikeDTO> getLike(int userId){
		return likeDao.selectByUserId(userId);
	}
	
	public void remove(LikeDTO likeDTO) {
		likeDao.delete(likeDTO);
	}
}
