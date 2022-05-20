package com.ssafy.ssafit.model.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.helper.constant.SocialLoginType;
import com.ssafy.ssafit.model.service.social.SocialOauth;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthService {
	private final List<SocialOauth> socialOauthList;
	private final HttpServletResponse response;

	public void request(SocialLoginType socialLoginType) {
		SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
		String redirectURL = socialOauth.getOauthRedirectURL();
		try {
			response.sendRedirect(redirectURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String requestAccessToken(SocialLoginType socialLoginType, String code) {
		SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
		return socialOauth.requestAccessToken(code);
	}

	private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
		return socialOauthList.stream()
				.filter(x -> x.type() == socialLoginType)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType입니다."));
	}
}
