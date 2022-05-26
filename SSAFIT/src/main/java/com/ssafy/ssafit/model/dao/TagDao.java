package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.TagDTO;

public interface TagDao {
	void insert(TagDTO tagDTO);
	List<TagDTO> selectAll();
	void delete(int id);
	TagDTO selectByName(String name);
}
