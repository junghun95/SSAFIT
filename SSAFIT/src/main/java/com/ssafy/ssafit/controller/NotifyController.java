package com.ssafy.ssafit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.NotifyDTO;
import com.ssafy.ssafit.model.service.NotifyService;
import com.ssafy.ssafit.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notify")
@RequiredArgsConstructor
public class NotifyController {
	private static final Logger log = LoggerFactory.getLogger(NotifyController.class);
	private final NotifyService notifyService;
	private final ResponseUtil responseUtil;
	
	@PostMapping("/")
	public ResponseEntity<?> create(@RequestBody NotifyDTO notifyDTO){ 
		log.info("NotifyController: create() called");
		notifyService.notice(notifyDTO);
		return new ResponseEntity<>(responseUtil.success("notify success"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> list(int userId){
		return new ResponseEntity<>(responseUtil.success(notifyService.getNotify(userId)), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> read(int id){
		notifyService.readNotify(id);
		return new ResponseEntity<>(responseUtil.success("readNotify success"), HttpStatus.ACCEPTED);
	}
}
