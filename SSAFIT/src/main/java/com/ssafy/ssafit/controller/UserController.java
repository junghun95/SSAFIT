package com.ssafy.ssafit.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.interceptor.JWTInterceptor;
import com.ssafy.ssafit.model.dto.UserDTO;
import com.ssafy.ssafit.model.dto.response.UserResponseDTO;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.util.JWTUtil;
import com.ssafy.ssafit.util.ResponseUtil;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api("유저 컨트롤러")
public class UserController {
	private final UserService userService;
	private final ResponseUtil responseUtil;
	private final JWTUtil jwtUtil;
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody UserDTO userDTO){
		userService.join(userDTO);
		return new ResponseEntity<>(responseUtil.success("join success"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> list(){
		return new ResponseEntity<>(responseUtil.success(
					userService.getAllUser().stream().map(u->UserResponseDTO.of(u)).collect(Collectors.toList())
				), 
				HttpStatus.ACCEPTED);
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<?> one(@PathVariable int id){
		return new ResponseEntity<>(responseUtil.success(
					UserResponseDTO.of(userService.getOneById(id))
				),
				HttpStatus.ACCEPTED);
	} 
	@GetMapping("/name/{username}")
	public ResponseEntity<?> oneByName(@PathVariable String username){
		return new ResponseEntity<>(responseUtil.success(
					UserResponseDTO.of(userService.getOneByUsername(username))
				),
				HttpStatus.ACCEPTED);
	} 
	
	@GetMapping("/follower")
	public ResponseEntity<?> follower(HttpServletRequest request){
		return new ResponseEntity<>(responseUtil.success(userService.getFollowers(jwtUtil.getUsername(request.getHeader(JWTInterceptor.HEADER_AUTH)))), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/follow")
	public ResponseEntity<?> follow(HttpServletRequest request){
		return new ResponseEntity<>(responseUtil.success(userService.getFollows(jwtUtil.getUsername(request.getHeader(JWTInterceptor.HEADER_AUTH)))), HttpStatus.ACCEPTED);
	} 
	
	
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody UserDTO userDTO){
		return new ResponseEntity<>(responseUtil.success(userService.modify(userDTO)), HttpStatus.ACCEPTED);
	} 
	
	@DeleteMapping("/remove")
	public ResponseEntity<?> remove(@PathVariable int id){
		userService.remove(id);
		return new ResponseEntity<>(responseUtil.success("delete success"), HttpStatus.ACCEPTED);
	} 
	
}
