package com.ssafy.ssafit.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.ZzimDTO;

public interface ZzimDao {
	List<ZzimDTO> selectAll(int userId);
	void insert(ZzimDTO zzimDTO);
	void delete(HashMap<String, Object> params);
}