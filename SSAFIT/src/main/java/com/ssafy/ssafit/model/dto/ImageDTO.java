package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {
	private int id;
	private String fileName;
	private String fileLocation;
	private int userId;
	private int boardImageId;
}
