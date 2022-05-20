package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.exception.UserAlreadyExists;
import com.ssafy.ssafit.exception.UserNotFound;
import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.UserDTO;
import com.ssafy.ssafit.model.dto.VideoDTO;

import lombok.RequiredArgsConstructor;

@Service("userService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
	private final UserDao userDAO;
	private final ZzimVideoService zzimVideoService;
	
	@Override
	@Transactional
	public void join(UserDTO userDTO) {
		checkDuplicateUser(userDTO);
		userDAO.insert(userDTO);
	}
	
	private void checkDuplicateUser(UserDTO userDTO) {
		if(getOneByUsername(userDTO.getUsername()) != null) throw new UserAlreadyExists("이미 존재하는 아이디입니다.");
		if(getOneByEmail(userDTO.getEmail()) != null) throw new UserAlreadyExists("이미 존재하는 e-mail입니다.");
	}
	@Override
	public List<UserDTO> getAllUser() {
		return userDAO.selectAll();
	}

	@Override
	@Transactional
	public UserDTO modify(UserDTO userDTO) {
		verifyUser(userDTO.getId());
		userDAO.update(userDTO);
		return userDTO;
	}

	@Override
	@Transactional
	public void remove(int id) {
		verifyUser(id);
		userDAO.delete(id);
	}
	
	private void verifyUser(int id) {
		if(getOneById(id) == null) throw new UserNotFound("존재하지 않는 유저입니다.");
	}

	@Override
	public List<UserDTO> getFollows(String username) {
		return userDAO.selectFollowsByUsername(username);
	}

	@Override
	public List<UserDTO> getFollowers(String username) {
		return userDAO.selectFollowersByUsername(username);
	}

	@Override
	public UserDTO getOneById(int id) {
		UserDTO user = userDAO.selectById(id).orElseThrow(()->new UserNotFound("존재하지 않는 유저입니다."));
		user.setFollowers(getFollowers(user.getUsername()));
		user.setFollows(getFollows(user.getUsername()));
		user.setLikeVideos(zzimVideoService.getZzimList(user.getId()));
		return user;
	}
	
	@Override
	public UserDTO getOneByUsername(String username) {
		UserDTO user = userDAO.selectByUsername(username).orElseThrow(()->new UserNotFound("존재하지 않는 유저입니다."));
		user.setFollowers(getFollowers(user.getUsername()));
		user.setFollows(getFollows(user.getUsername()));
		user.setLikeVideos(zzimVideoService.getZzimList(user.getId()));
		return userDAO.selectByUsername(username).orElse(null);
	}

	@Override
	public UserDTO getOneByEmail(String email) {
		UserDTO user = userDAO.selectByEmail(email).orElseThrow(()->new UserNotFound("존재하지 않는 유저입니다."));
		user.setFollowers(getFollowers(user.getUsername()));
		user.setFollows(getFollows(user.getUsername()));
		user.setLikeVideos(zzimVideoService.getZzimList(user.getId()));
		return userDAO.selectByEmail(email).orElse(null);		
	}

	
}
