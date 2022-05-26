package com.ssafy.ssafit.model.dto;



public enum DType {
	NOTICE("Notice"), REVIEW("Review"), LIKE("Like"), TAG("Tag"), DM("Dm"), REPORT("report");
	private String value;
	
	DType(String value) {
		this.value = value;
	}
	
	public static DType fromString(String str) {
		if(str == null) return null;
		for(DType value : DType.values()) {
			if(str.equalsIgnoreCase(value.value)) return value;
		}
		return null;
	}
}
