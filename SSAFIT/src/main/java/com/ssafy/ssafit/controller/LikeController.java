package com.ssafy.ssafit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
//	@PostMapping("/")
//	public ResponseEntity<?>() like(){
//		
//	}
}
