package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.NotifyDao;
import com.ssafy.ssafit.model.dto.NotifyDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotifyService {
	private final NotifyDao notifyDao;
	
	public void notice(NotifyDTO notifyDTO) {
		notifyDao.insert(notifyDTO);
	}
	
	public List<NotifyDTO> getNotify(int userId){
		return notifyDao.selectByUserId(userId);
	}
	
	public void readNotify(int id) {
		notifyDao.update(id);
	}
}
