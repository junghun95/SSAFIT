package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ZzimDao;
import com.ssafy.ssafit.model.dto.ZzimDTO;

import lombok.RequiredArgsConstructor;

@Service("zzimService")
@RequiredArgsConstructor
public class ZzimServiceImpl implements ZzimService{

	private final ZzimDao zzimDao;
	
	@Override
	public List<ZzimDTO> getZzimList(int userId) {
		return zzimDao.selectAll(userId);
	}

	@Override
	public void createZzim(ZzimDTO zzimDTO) {
		zzimDao.insert(zzimDTO);
	}

	@Override
	public void removeZzim(int id) {
		zzimDao.delete(id);
	}

}
