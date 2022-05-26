package com.ssafy.ssafit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.ssafit.interceptor.JWTInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("POST","GET","DELETE","PUT","PATCH");
	}
	
	@Autowired
	private JWTInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/api/auth/login/**", 
						"/swagger-ui/**", 
						"/swagger-resources/**", 
						"/v3/api-docs/**",
						"/api/auth/social/**",
						"/api/user/join/**",
						"/api/board/{id}",
						"/api/board/list",
						"/api/user/name/**",
						"/api/review/video/**",
						"/api/user/email/**");
	}
}