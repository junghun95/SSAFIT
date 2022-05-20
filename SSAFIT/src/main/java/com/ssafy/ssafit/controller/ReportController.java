package com.ssafy.ssafit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.ReportDTO;
import com.ssafy.ssafit.model.service.ReportService;
import com.ssafy.ssafit.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {
	private final ReportService reportService;
	private final ResponseUtil responseUtil;
	
	@PostMapping("/")
	public ResponseEntity<?> report(@RequestBody ReportDTO reportDTO){
		reportService.report(reportDTO);
		return new ResponseEntity<>(responseUtil.success("report success"), HttpStatus.ACCEPTED);
	}
}
