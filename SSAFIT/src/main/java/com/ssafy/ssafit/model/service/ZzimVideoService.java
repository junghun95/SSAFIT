package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.response.VideoRequestDTO;
import com.ssafy.ssafit.model.dto.response.VideoResponseDTO;

public interface ZzimVideoService {
	void createZzim(VideoRequestDTO videoRequestDTO);
	List<VideoResponseDTO> getZzimList(int userId);
	void remove(int id);
}
