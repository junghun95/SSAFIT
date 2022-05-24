package com.ssafy.ssafit.model.dto.response;

import java.util.List;

import com.ssafy.ssafit.model.dto.ImageDTO;
import com.ssafy.ssafit.model.dto.LikeDTO;
import com.ssafy.ssafit.model.dto.NotifyDTO;
import com.ssafy.ssafit.model.dto.ReportDTO;
import com.ssafy.ssafit.model.dto.ReviewDTO;
import com.ssafy.ssafit.model.dto.UserDTO;
import com.ssafy.ssafit.model.dto.ZzimDTO;

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
	
	public static UserResponseDTO of(UserDTO userDTO) {
		return UserResponseDTO.builder()
				.id(userDTO.getId())
				.username(userDTO.getUsername())
				.email(userDTO.getEmail())
				.regDate(userDTO.getRegDate())
				.role(userDTO.getRole())
				.followCnt(userDTO.getFollowCnt())
				.followerCnt(userDTO.getFollowerCnt())
				.follows(userDTO.getFollows())
				.followers(userDTO.getFollowers())
				.reviews(userDTO.getReviews())
				.reports(userDTO.getReports())
				.notifies(userDTO.getNotifies())
				.likes(userDTO.getLikes())
				.images(userDTO.getImages())
				.zzims(userDTO.getZzims())
				.build();
	}

}
