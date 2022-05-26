package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.TagDao;
import com.ssafy.ssafit.model.dto.TagDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {
	private final TagDao tagDao;
	
	public List<TagDTO> getTag(){
		return tagDao.selectAll();
	}
	
	public void create(TagDTO tagDTO) {
		tagDao.insert(tagDTO);
	}
	
	public void removeTag(int id) {
		tagDao.delete(id);
	}
	public List<TagDTO> getVideoTagByUserId(int userId) {
		return tagDao.selectVideoTagsByUserId(userId);
	}
}
