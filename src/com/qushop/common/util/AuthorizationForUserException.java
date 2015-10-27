package com.qushop.common.util;

public class AuthorizationForUserException extends IllegalAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409495256645691228L;
	
	public AuthorizationForUserException() {
		super();
	}

	public AuthorizationForUserException(String errorMsg) {
		super(errorMsg);
	}

}
