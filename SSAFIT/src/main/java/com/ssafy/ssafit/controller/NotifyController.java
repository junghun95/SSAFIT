package com.ssafy.ssafit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
//	@PostMapping("/")
//	public ResponseEntity<?> create(){ 
//		log.info("NotifyController: create() called");
//	}

}
