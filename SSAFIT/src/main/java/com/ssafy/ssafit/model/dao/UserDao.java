package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.UserDTO;

public interface UserDao {
	public void insert(UserDTO user);
	public UserDTO selectById(int id);
	public List<UserDTO> selectAll();
	public void delete(int id);
	public void update(UserDTO user);
	public List<UserDTO> selectFollowsByUsername(String username);
	public List<UserDTO> selectFollowersByUsername(String username);
}
