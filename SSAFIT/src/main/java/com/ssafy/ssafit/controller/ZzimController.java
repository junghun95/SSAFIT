package com.ssafy.ssafit.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.ZzimDTO;
import com.ssafy.ssafit.model.service.ZzimService;
import com.ssafy.ssafit.util.ResponseUtil;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/zzim")
@RequiredArgsConstructor
@Api("찜 컨트롤러")
public class ZzimController {
	private final ZzimService zzimService;
	private final ResponseUtil responseUtil;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> list(@PathVariable int userId){
		return new ResponseEntity<>(responseUtil.success(
				zzimService.getZzimList(userId)
				), 
				HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ZzimDTO zzimDTO){
		zzimService.createZzim(zzimDTO);
		return new ResponseEntity<>(responseUtil.success("zzim success"), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/remove/{videoId}/{userId}")
	public ResponseEntity<?> remove(@PathVariable String videoId, @PathVariable int userId){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("videoId", videoId);
		params.put("userId", userId);
		zzimService.removeZzim(params);
		return new ResponseEntity<>(responseUtil.success("delete zzim success"), HttpStatus.ACCEPTED);
	}
}