package com.ssafy.ssafit.exception;

public class UserAlreadyExists extends RuntimeException{

	public UserAlreadyExists() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExists(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExists(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExists(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
