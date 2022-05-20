package com.ssafy.ssafit.model.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.ssafit.model.dto.UserDTO;
import com.ssafy.ssafit.model.dto.VideoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
	private int id;
	private String username;
	private String email;
	private String regDate;
	private String role;

	private List<UserDTO> follows;
	private List<UserDTO> followers;
	private List<VideoResponseDTO> likeVideos;
	
	public static UserResponseDTO of(UserDTO userDTO) {
		return UserResponseDTO.builder()
				.id(userDTO.getId())
				.username(userDTO.getUsername())
				.email(userDTO.getEmail())
				.regDate(userDTO.getRegDate())
				.role("USER")
				.follows(userDTO.getFollows())
				.followers(userDTO.getFollowers())
				.likeVideos(userDTO.getLikeVideos().stream().map(v->VideoResponseDTO.of(v)).collect(Collectors.toList()))
				.build();
	}

}
