package com.ssafy.ssafit.model.dao;

public interface FollowDao {
	public void insert(String from, String to);
	public void delete(String from, String to);
}
