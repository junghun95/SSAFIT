package com.ssafy.ssafit.model.service.social;

import org.springframework.http.ResponseEntity;

import com.ssafy.ssafit.helper.constant.SocialLoginType;

public interface SocialOauth {
	String getOauthRedirectURL();
	
	String requestAccessToken(String code);
	
	String getEmail(ResponseEntity<?> responseEntity) throws Exception;
	
	default SocialLoginType type() {
		if(this instanceof GoogleOauth) {
			return SocialLoginType.google;
		} else {
			return null;
		}
	}
}
