package com.ssafy.ssafit.helper.converter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.ssafy.ssafit.helper.constant.SocialLoginType;

@Configuration
public class SocialLoginTypeConverter implements Converter<String , SocialLoginType>{

	@Override
	public SocialLoginType convert(String source) {
		return SocialLoginType.valueOf(source.toUpperCase());
	}

}
