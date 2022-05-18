package com.ssafy.ssafit.model.dao;

import java.util.List;
import java.util.Optional;

import com.ssafy.ssafit.model.dto.UserDTO;

public interface UserDao {
	public void insert(UserDTO user);
	public Optional<UserDTO> selectById(int id);
	public Optional<UserDTO> selectByUsername(String username);
	public Optional<UserDTO> selectByEmail(String email);
	public List<UserDTO> selectAll();
	public void delete(int id);
	public void update(UserDTO user);
	public List<UserDTO> selectFollowsByUsername(String username);
	public List<UserDTO> selectFollowersByUsername(String username);
}
