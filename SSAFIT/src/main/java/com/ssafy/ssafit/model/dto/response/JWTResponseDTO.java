package com.ssafy.ssafit.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JWTResponseDTO {
	private String accessToken;
	
	public static JWTResponseDTO of(String token) {
		return JWTResponseDTO.builder()
				.accessToken(token)
				.build();
	}
	
}
