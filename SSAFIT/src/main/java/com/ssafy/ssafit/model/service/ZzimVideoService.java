package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.VideoDTO;
import com.ssafy.ssafit.model.dto.request.VideoRequestDTO;

public interface ZzimVideoService {
	void createZzim(VideoRequestDTO videoRequestDTO);
	List<VideoDTO> getZzimList(int userId);
	void remove(int id);
}
