package com.ssafy.ssafit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.helper.constant.SocialLoginType;
import com.ssafy.ssafit.model.service.OauthService;
import com.ssafy.ssafit.util.ResponseUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/auth/social")
@Slf4j
public class OauthController {
	private final OauthService oauthService;
	private final ResponseUtil responseUtil;
	
	@GetMapping("/{socialLoginType}")
	public void socialLoginType(@PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
		log.info(">> 사용자로 부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
		oauthService.request(socialLoginType);
	}
	
	@GetMapping("/{socialLoginType}/callback")
	public ResponseEntity<?> callback(
			@PathVariable(name="socialLoginType") SocialLoginType socialLoginType, 
			@RequestParam(name = "code") String code) {
		log.info(">> 소셜 로그인 API 서버로부터 받은 code : {}", code);
		String email = oauthService.requestAccessToken(socialLoginType, code);
		return new ResponseEntity<>(responseUtil.success("socialLogin success", email), HttpStatus.ACCEPTED);
	}	

}
