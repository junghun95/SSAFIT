package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZzimDTO {
	private int id;
	private String videoId;
	private int userId;
	private int tagId;
	private String tagName;
	private String title;
	private String channelTitle;
	private String description;
	private String publishTime;
}
