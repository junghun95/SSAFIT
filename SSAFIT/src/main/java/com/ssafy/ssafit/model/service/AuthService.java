package com.ssafy.ssafit.model.service;

public interface AuthService {
	boolean check(String username, String password) throws Exception;
}
