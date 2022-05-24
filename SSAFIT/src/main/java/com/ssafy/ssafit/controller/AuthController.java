package com.ssafy.ssafit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.request.LoginRequestDTO;
import com.ssafy.ssafit.model.dto.response.JWTResponseDTO;
import com.ssafy.ssafit.model.dto.response.LoginResponseDTO;
import com.ssafy.ssafit.model.service.AuthService;
import com.ssafy.ssafit.util.JWTUtil;
import com.ssafy.ssafit.util.ResponseUtil;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api("로그인 컨트롤러")
public class AuthController {
	private final JWTUtil jwtUtil;
	private final ResponseUtil responseUtil;
	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO){
		try {
			if (authService.check(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())) {
				LoginResponseDTO loginResponseDTO = authService.getLoginResponseDTO(loginRequestDTO.getUsername(), loginRequestDTO.getPassword(), JWTResponseDTO.of(jwtUtil.createToken("username", loginRequestDTO.getUsername())));
				return new ResponseEntity<>(responseUtil.success("login success", loginResponseDTO), HttpStatus.ACCEPTED);
			} else {			
				return new ResponseEntity<>(responseUtil.success("fail"), HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(responseUtil.success("fail"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
