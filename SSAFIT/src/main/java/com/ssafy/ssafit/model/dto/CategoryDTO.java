package com.ssafy.ssafit.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
	private int id;
	private String name;
	private CategoryDTO par;
	private List<CategoryDTO> children;
}
