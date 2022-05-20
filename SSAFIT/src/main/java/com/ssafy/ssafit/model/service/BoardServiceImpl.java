package com.ssafy.ssafit.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.exception.BoardNotExistsException;
import com.ssafy.ssafit.model.dao.BoardDao;
import com.ssafy.ssafit.model.dto.BoardDTO;

import lombok.RequiredArgsConstructor;

@Service("boardService")
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{
	private final BoardDao boardDao;

	@Override
	public void write(BoardDTO boardDTO) {
		boardDao.insert(boardDTO);
	}

	@Override
	public void remove(int id) {
		boardDao.delete(id);
	}

	@Override
	public void modify(BoardDTO boardDTO) {
		boardDao.update(boardDTO);
		
	}

	@Override
	@Transactional(readOnly = true)
	public BoardDTO getOneById(int id) {
		BoardDTO boardDTO = boardDao.selectById(id);
		if(boardDTO == null) throw new BoardNotExistsException("존재하지 않는 게시글입니다.");
		return boardDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BoardDTO> getAll(Map<String, String> params) {
		return boardDao.selectAll(params);
	}

	@Override
	public BoardDTO read(int id) {
		BoardDTO board = getOneById(id);
		System.out.println("board : "+ board);
		board.setViewCnt(board.getViewCnt()+1);
		modify(board);
		return board;
	}
	
	
}
