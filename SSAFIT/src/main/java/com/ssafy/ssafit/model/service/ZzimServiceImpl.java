package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.TagDao;
import com.ssafy.ssafit.model.dao.ZzimDao;
import com.ssafy.ssafit.model.dto.TagDTO;
import com.ssafy.ssafit.model.dto.ZzimDTO;

import lombok.RequiredArgsConstructor;

@Service("zzimService")
@RequiredArgsConstructor
public class ZzimServiceImpl implements ZzimService{

	private final ZzimDao zzimDao;
	private final TagDao tagDao;
	
	@Override
	public List<ZzimDTO> getZzimList(int userId) {
		return zzimDao.selectAll(userId);
	}

	@Override
	public void createZzim(ZzimDTO zzimDTO) {
		zzimDao.insert(zzimDTO);
		TagDTO tagDTO = TagDTO.builder()
							.name(zzimDTO.getTagName())
							.build();
		tagDao.insert(tagDTO);
	}

	@Override
	public void removeZzim(HashMap<String, Object> params) {
		zzimDao.delete(params);
	}

}
