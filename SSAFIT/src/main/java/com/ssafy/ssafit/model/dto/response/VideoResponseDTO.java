package com.ssafy.ssafit.model.dto.response;

import com.ssafy.ssafit.model.dto.VideoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponseDTO {
	private String videoId;
	private String title;
	private String part;
	
	public static VideoResponseDTO of(VideoDTO videoDTO) {
		return VideoResponseDTO.builder()
				.videoId(videoDTO.getId())
				.title(videoDTO.getTitle())
				.part(videoDTO.getPart().getName())
				.build();
	}
}
