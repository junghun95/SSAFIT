package com.ssafy.ssafit.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequestDTO {
	private String videoId;
	private String title;
	private String channelName;
	private String url;
	private int userId;
	private int partId;
}
