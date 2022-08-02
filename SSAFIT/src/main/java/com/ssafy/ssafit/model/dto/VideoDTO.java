package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO {
	private String id;
	private String title;
	private String channelName;
	private String url;
	
	private PartDTO part;
}
