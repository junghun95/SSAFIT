package com.ssafy.ssafit.model.dto.response;

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
}
