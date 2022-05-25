package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.ZzimDTO;

public interface ZzimService {
	List<ZzimDTO> getZzimList(int userId);
	void createZzim(ZzimDTO zzimDTO);
	void removeZzim(HashMap<String, Object> params);
}
