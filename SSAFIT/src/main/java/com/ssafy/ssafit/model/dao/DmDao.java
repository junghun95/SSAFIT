package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.DmDTO;

public interface DmDao {
	void insert(DmDTO dmDTO);
	List<DmDTO> selectByUserId(int userId);
	void delete(int id);
}
