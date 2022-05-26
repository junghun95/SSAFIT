package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.response.JWTResponseDTO;
import com.ssafy.ssafit.model.dto.response.LoginResponseDTO;

public interface AuthService {
	boolean check(String username, String password) throws Exception;

	LoginResponseDTO getLoginResponseDTO(String username, String password, JWTResponseDTO jwtResponseDTO);

	boolean checkSocial(String username, String password) throws Exception;
}
