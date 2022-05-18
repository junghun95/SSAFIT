package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	private int id;
	private String content;
	private int userId;
	private String regDate;
	private String videoId;
	private int boardId;
}
