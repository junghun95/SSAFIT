package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.BoardDTO;

public interface BoardDao {
	public void insert(BoardDTO boardDTO);
	public void delete(int id);
	public void update(BoardDTO boardDTO);
	
	public BoardDTO selectById(int id);
	public List<BoardDTO> selectAll(Map<String, Object> params);
}
