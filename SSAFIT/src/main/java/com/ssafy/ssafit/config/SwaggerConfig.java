package com.ssafy.ssafit.config;


import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket openApi() {
		return new Docket(DocumentationType.OAS_30)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.ssafit.controller"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(apiInfo())
				.securityContexts(securityContexts())
				.securitySchemes(securitySchemes());
	}
	private List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder()
				.securityReferences(securityReferences())
				.build());
	}
	
	private List<SecurityReference> securityReferences(){ 
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		
		return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
	}
	
	private List<SecurityScheme> securitySchemes() {
		return Arrays.asList(new ApiKey("Authorization", "Authorization", "header"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SSAFIT 헬스 어플리케이션")
				.description("운동을 사랑하는 사람들을 위한 매장 정보, 운동 정보 및 영상과 제품 구매 어플리케이션")
				.version("snapshot-1.0.0")
				.build();
	}

}
