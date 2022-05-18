package com.ssafy.ssafit.model.dto;

import java.sql.Timestamp;
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
	private String regDate;
	
	private List<UserDTO> follows;
	private List<UserDTO> followers;
	private List<VideoDTO> likeVideos;
}
