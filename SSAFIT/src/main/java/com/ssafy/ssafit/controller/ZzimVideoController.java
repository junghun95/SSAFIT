package com.ssafy.ssafit.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.request.VideoRequestDTO;
import com.ssafy.ssafit.model.dto.response.VideoResponseDTO;
import com.ssafy.ssafit.model.service.ZzimVideoService;
import com.ssafy.ssafit.util.ResponseUtil;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/zzim")
@RequiredArgsConstructor
@Api("찜 컨트롤러")
public class ZzimVideoController {
	private final ZzimVideoService zzimVideoService;
	private final ResponseUtil responseUtil;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> list(@PathVariable int userId){
		return new ResponseEntity<>(responseUtil.success(
					zzimVideoService.getZzimList(userId).stream().map(v->VideoResponseDTO.of(v)).collect(Collectors.toList())
				),
				HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> remove(@PathVariable int id) {
		zzimVideoService.remove(id);
		return new ResponseEntity<>(responseUtil.success("delete success"), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> zzim(VideoRequestDTO videoRequestDTO){
		zzimVideoService.createZzim(videoRequestDTO);
		return new ResponseEntity<>(responseUtil.success("zzim success"), HttpStatus.ACCEPTED);
	}
}