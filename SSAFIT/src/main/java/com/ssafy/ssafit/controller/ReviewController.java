package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.ReviewDTO;
import com.ssafy.ssafit.model.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/video/{id}")
	public List<ReviewDTO> videoReviewList(@PathVariable String id) {
		return reviewService.getVideoReviewList(id);
	}
	
	@GetMapping("/board/{id}")
	public List<ReviewDTO> boardReviewList(@PathVariable int id) {
		return reviewService.getBoardReviewList(id);
	}
}
