package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.DmDao;
import com.ssafy.ssafit.model.dto.DmDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DmService {
	private final DmDao dmDao;
	
	public List<DmDTO> getDm(int userId){
		return dmDao.selectByUserId(userId);
	}
	
	public void create(DmDTO dmDTO) {
		dmDao.insert(dmDTO);
	}
	
	public void removeDm(int id) {
		dmDao.delete(id);
	}
}
