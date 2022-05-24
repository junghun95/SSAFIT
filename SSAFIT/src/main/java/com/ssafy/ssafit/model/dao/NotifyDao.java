package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.NotifyDTO;

public interface NotifyDao {
	void insert(NotifyDTO notifyDTO);
	NotifyDTO selectByUerId(int id);
	void update(int id);
}
