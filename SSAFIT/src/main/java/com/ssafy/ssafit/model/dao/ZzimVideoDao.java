package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.ZzimVideoDTO;
import com.ssafy.ssafit.model.dto.response.VideoResponseDTO;

public interface ZzimVideoDao {
	void insert(ZzimVideoDTO zzimVideo);
	void delete(int id);
	List<VideoResponseDTO> selectAll(int userId);
}
