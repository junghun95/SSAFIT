package com.ssafy.ssafit.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private int id;
	private String username;
	private String password;
	private String email;
	private LocalDate regDate;
	
	private List<UserDTO> follows = new ArrayList<>();
	private List<UserDTO> followers = new ArrayList<>();
	private List<VideoDTO> likeVideos = new ArrayList<>();
}
