package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DmDTO {
	private int id;
	private int fromUserId;
	private int toUserId;
	private String content;
	private String regDate;
}
