package com.ssafy.ssafit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.LikeDTO;
import com.ssafy.ssafit.model.service.LikeService;
import com.ssafy.ssafit.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {
	private static final Logger log = LoggerFactory.getLogger(LikeController.class);
	private final LikeService likeService;
	private final ResponseUtil responseUtil;
	
	@PostMapping("/")
	public ResponseEntity<?> like(@RequestBody LikeDTO likeDTO){
		likeService.doLike(likeDTO);
		return new ResponseEntity<>(responseUtil.success("like success"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> list(int userId){
		return new ResponseEntity<>(responseUtil.success(likeService.getLike(userId)), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> remove(@RequestBody LikeDTO likeDTO){
		likeService.remove(likeDTO);
		return new ResponseEntity<>(responseUtil.success("delete like success"), HttpStatus.ACCEPTED);
	}
}
