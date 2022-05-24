package com.ssafy.ssafit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<?> list(int userId){
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
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> remove(int id){
		zzimService.removeZzim(id);
		return new ResponseEntity<>(responseUtil.success("delete zzim success"), HttpStatus.ACCEPTED);
	}
}
