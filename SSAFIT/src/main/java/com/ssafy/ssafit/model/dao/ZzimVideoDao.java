package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.VideoDTO;
import com.ssafy.ssafit.model.dto.ZzimDTO;

public interface ZzimVideoDao {
	void insert(ZzimDTO zzimVideo);
	void delete(int id);
	List<VideoDTO> selectAll(int userId);
}
