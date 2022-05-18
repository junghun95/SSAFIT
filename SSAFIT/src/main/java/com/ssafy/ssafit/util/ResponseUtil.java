package com.ssafy.ssafit.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
	public Map<String, Object> success(String msg){
		return success(msg, Collections.emptyList());
	}
	public Map<String, Object> success(Object data){
		return success("",data);
	}
	public Map<String, Object> success(String msg, Object data){
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("message", msg);
		map.put("items", data);
		return map;
	}
	
}
