package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.dto.BoardDTO;

public interface BoardService {
	public void write(BoardDTO boardDTO);
	public void remove(int id);
	public void modify(BoardDTO boardDTO);
	
	public BoardDTO read(int id);
	public BoardDTO getOneById(int id);
	public List<BoardDTO> getAll(Map<String, Object> params);
}
