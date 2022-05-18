package com.ssafy.ssafit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.FollowDTO;
import com.ssafy.ssafit.model.service.FollowService;
import com.ssafy.ssafit.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/follow")
@RestController
@RequiredArgsConstructor
@Api("팔로우 컨트롤러")
public class FollowController {
	private final FollowService followService;
	private final ResponseUtil responseUtil;
	
	@PostMapping("")
	public ResponseEntity<?> follow(@RequestBody FollowDTO followDTO){
		followService.addFollow(followDTO);
		return new ResponseEntity<>(responseUtil.success("follow accept") ,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/cancle")
	public ResponseEntity<?> cancle(@RequestBody FollowDTO followDTO){
		followService.removeFollow(followDTO);
		return new ResponseEntity<>(responseUtil.success("follow cancle") ,HttpStatus.ACCEPTED);
	}
}
