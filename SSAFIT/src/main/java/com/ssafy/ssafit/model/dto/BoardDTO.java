package com.ssafy.ssafit.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
	private int id;
	private String title;
	private String content;
	private String regDate;
	private int viewCnt;
	
	private UserDTO user;
	private List<PartDTO> parts;
	private List<ReviewDTO> reviews;
}
