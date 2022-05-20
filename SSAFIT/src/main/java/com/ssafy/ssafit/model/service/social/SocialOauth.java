package com.ssafy.ssafit.model.service.social;

import com.ssafy.ssafit.helper.constant.SocialLoginType;

public interface SocialOauth {
	String getOauthRedirectURL();
	
	String requestAccessToken(String code);
	
	default SocialLoginType type() {
		if(this instanceof GoogleOauth) {
			return SocialLoginType.google;
		} else {
			return null;
		}
	}
}
