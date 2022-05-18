package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.UserDTO;

public interface UserService {
	public void join(UserDTO userDTO);
	public List<UserDTO> getAllUser();
	public UserDTO getOneById(int id);
	public UserDTO getOneByUsername(String username);
	public UserDTO getOneByEmail(String email);
	public UserDTO modify(UserDTO userDTO);
	public void remove(int id);
	public List<UserDTO> getFollows(String username);
	public List<UserDTO> getFollowers(String username);
	
}
