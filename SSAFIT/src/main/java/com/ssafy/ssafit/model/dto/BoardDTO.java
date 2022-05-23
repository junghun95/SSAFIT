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
	private int reviewCnt;
	private int likeCnt;
	private String deleteDate;
	
	private UserDTO user;
	private CategoryDTO category;
	private List<TagDTO> tags;
	private List<ReviewDTO> reviews;
	private List<ImageDTO> images;
}
