package com.ssafy.ssafit.model.dto.response;

import java.util.List;

import com.ssafy.ssafit.model.dto.ReviewDTO;
import com.ssafy.ssafit.model.dto.ZzimDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
	private String username;
	private String email;
	private String regDate;
	private String authorization;
	private List<ReviewDTO> reviews;
	private List<ZzimDTO> zzims;
}
