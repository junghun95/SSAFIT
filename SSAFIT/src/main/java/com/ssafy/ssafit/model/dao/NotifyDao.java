package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.NotifyDTO;

public interface NotifyDao {
	void insert(NotifyDTO notifyDTO);
	List<NotifyDTO> selectByUserId(int id);
	void update(int id);
}
