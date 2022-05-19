package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ZzimVideoDao;
import com.ssafy.ssafit.model.dto.ZzimVideoDTO;

@Service
public class ZzimVideoServiceImpl implements ZzimVideoService{

	private ZzimVideoDao zzimVideoDao;
	@Override
	public void createZzim(ZzimVideoDTO zzimVideo) {
		zzimVideoDao.insert(zzimVideo);
	}

	@Override
	public void removeZzim(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ZzimVideoDTO> getZzimList(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
