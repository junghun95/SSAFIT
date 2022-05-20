package com.ssafy.ssafit.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.ReviewDTO;
import com.ssafy.ssafit.model.service.ReviewService;

import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.util.ResponseUtil;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
@Api("리뷰 컨트롤러")
public class ReviewController {
	private final ReviewService reviewService;
	private final ResponseUtil responseUtil;

	@GetMapping("/video/{id}")
	public ResponseEntity<Map<String, Object>> videoReviewList(@PathVariable String id) {
		return new ResponseEntity<Map<String,Object>>(
				responseUtil.success(reviewService.getVideoReviewList(id))
				, HttpStatus.ACCEPTED
		);
	}
	
	@GetMapping("/board/{id}")
	public ResponseEntity<Map<String, Object>> boardReviewList(@PathVariable int id) {
		return new ResponseEntity<Map<String,Object>>(
				responseUtil.success(reviewService.getBoardReviewList(id))
				, HttpStatus.ACCEPTED
		);
	}
	
	@PostMapping("/write")
	public ResponseEntity<Map<String, Object>> write(ReviewDTO review){
		reviewService.writeReview(review);
		return new ResponseEntity<>(responseUtil.success("write review"), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Map<String, Object>> remove(@PathVariable int id){
		reviewService.removeReview(id);
		return new ResponseEntity<Map<String,Object>>(responseUtil.success("remove review"), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(ReviewDTO review){
		reviewService.modifyReview(review);
		return new ResponseEntity<Map<String,Object>>(responseUtil.success("modify review"), HttpStatus.ACCEPTED);

	}
}
