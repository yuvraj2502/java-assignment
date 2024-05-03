package com.exception;

public class InvalidCustomerIdException extends Exception{

	private static final long serialVersionUID = 4767969208760401614L;
	private String message;

	public InvalidCustomerIdException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
		
}
