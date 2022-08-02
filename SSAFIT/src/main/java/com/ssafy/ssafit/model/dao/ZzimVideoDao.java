package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.VideoDTO;
import com.ssafy.ssafit.model.dto.ZzimVideoDTO;

public interface ZzimVideoDao {
	void insert(ZzimVideoDTO zzimVideo);
	void delete(int id);
	List<VideoDTO> selectAll(int userId);
}
