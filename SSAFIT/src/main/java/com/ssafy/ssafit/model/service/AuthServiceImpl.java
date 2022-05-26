package com.ssafy.ssafit.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.exception.PWIncorrectException;
import com.ssafy.ssafit.exception.UserNotFound;
import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.UserDTO;
import com.ssafy.ssafit.model.dto.response.JWTResponseDTO;
import com.ssafy.ssafit.model.dto.response.LoginResponseDTO;
import com.ssafy.ssafit.util.SHA256;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserDao userDao;
	
	@Override
	public boolean check(String username, String password) throws Exception{
		UserDTO userDTO = userDao.selectByUsername(username);
		if(userDTO == null) {
			throw new UserNotFound("존재하지 않는 유저입니다.");
		}
		if(userDTO.getPassword().equals(new SHA256().getHash(password))) {
			return true;
		}else {
			throw new PWIncorrectException();
		}
	}
	
	@Override
	public boolean checkSocial(String username, String password) throws Exception{
		UserDTO userDTO = userDao.selectByUsername(username);
		if(userDTO == null) {
			throw new UserNotFound("존재하지 않는 유저입니다.");
		}
		if(userDTO.getPassword().equals(password)) {
			return true;
		}else {
			throw new PWIncorrectException();
		}
	}

	@Override
	public LoginResponseDTO getLoginResponseDTO(String username, String password, JWTResponseDTO jwtResponseDTO) {
		UserDTO userDTO = userDao.selectByUsername(username);
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.builder()
						.username(username)
						.email(userDTO.getEmail())
						.regDate(userDTO.getRegDate())
						.authorization(jwtResponseDTO.getAccessToken())
						.reviews(userDTO.getReviews())
						.zzims(userDTO.getZzims())
						.build();
		return loginResponseDTO;
	}

}
