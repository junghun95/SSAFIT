package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.ZzimVideoDTO;

public interface ZzimVideoService {
	void createZzim(ZzimVideoDTO zzimVideoDTO);
	void removeZzim(int id);
	List<ZzimVideoDTO> getZzimList(int userId);
}
