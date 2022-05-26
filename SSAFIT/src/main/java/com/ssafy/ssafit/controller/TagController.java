package com.ssafy.ssafit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.TagDTO;
import com.ssafy.ssafit.model.service.TagService;
import com.ssafy.ssafit.util.ResponseUtil;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
@Api("태그 컨트롤러")
public class TagController {
	private final TagService tagService;
	private final ResponseUtil responseUtil;
	
	@PutMapping("/")
	public ResponseEntity<?> create(@RequestBody String tagName){
		tagService.create(TagDTO.builder().name(tagName).build());
		return new ResponseEntity<>(responseUtil.success("insert tag success"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> list(){
		return new ResponseEntity<>(responseUtil.success(tagService.getTag()), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/video/user/{id}")
	public ResponseEntity<?> vtagnames(@PathVariable int id){
		return new ResponseEntity<>(responseUtil.success("success", tagService.getVideoTagByUserId(id)), HttpStatus.ACCEPTED);
	}
}