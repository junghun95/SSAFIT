package com.ssafy.ssafit.model.dto.response;

import java.util.List;

import com.ssafy.ssafit.model.dto.BoardDTO;
import com.ssafy.ssafit.model.dto.CategoryDTO;
import com.ssafy.ssafit.model.dto.ImageDTO;
import com.ssafy.ssafit.model.dto.TagDTO;
import com.ssafy.ssafit.model.dto.ReviewDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseDTO {
		private int id;
		private String title;
		private String content;
		private String writer;
		private String regDate;
		private int viewCnt;
		
		private CategoryDTO category;
		private List<TagDTO> tags;
		private List<ReviewDTO> reviews;
		private List<ImageDTO> images;

		public static BoardResponseDTO of(BoardDTO boardDTO) {
			return BoardResponseDTO.builder()
					.id(boardDTO.getId())
					.title(boardDTO.getTitle())
					.content(boardDTO.getContent())
					.writer(boardDTO.getUser().getUsername())
					.viewCnt(boardDTO.getViewCnt())
					.tags(boardDTO.getTags())
					.category(boardDTO.getCategory())
					.images(boardDTO.getImages())
					.reviews(boardDTO.getReviews()).build();
		}
	
}
