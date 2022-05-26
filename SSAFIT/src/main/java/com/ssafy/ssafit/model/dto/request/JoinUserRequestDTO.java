package com.ssafy.ssafit.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinUserRequestDTO {
	private String username;
	private String password;
	private String email;
}
