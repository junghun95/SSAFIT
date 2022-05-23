package com.ssafy.ssafit.model.dto;

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
	private String role;
	private int followCnt;
	private int followerCnt;
	
	private List<ZzimDTO> zzims;
	private List<UserDTO> follows;
	private List<UserDTO> followers;
	private List<ReviewDTO> reviews;
	private List<ReportDTO> reports;
	private List<NotifyDTO> notifies;
	private List<LikeDTO> likes;
	private List<ImageDTO> images;
	
}
