package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.BoardDTO;
import com.ssafy.ssafit.model.service.BoardService;
import com.ssafy.ssafit.util.ResponseUtil;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api("BoardController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
	private final BoardService boardService;
	private final ResponseUtil responseUtil;
	
	
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody BoardDTO boardDTO){
		boardService.write(boardDTO);
		return new ResponseEntity<>(responseUtil.success("board write success"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> one(@PathVariable int id){
		return new ResponseEntity<>(responseUtil.success(boardService.read(id)), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> list(
								@RequestParam(defaultValue = "0") String mode,
								@RequestParam(required = false, defaultValue = "") String key,
								@RequestParam(required = false, defaultValue = "") String parts){
		Map<String, String>params = new HashMap<>();
		params.put("mode",mode);
		params.put("key",key);
		params.put("parts",parts);
		return new ResponseEntity<>(responseUtil.success(boardService.getAll(params)), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody BoardDTO boardDTO){
		boardService.modify(boardDTO);
		return new ResponseEntity<>(responseUtil.success("board update success"), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> write(@PathVariable int id){
		boardService.remove(id);
		return new ResponseEntity<>(responseUtil.success("board delete success"), HttpStatus.ACCEPTED);
	}
}
