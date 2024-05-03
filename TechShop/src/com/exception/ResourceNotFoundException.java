package com.exception;

public class ResourceNotFoundException extends Exception {


	private static final long serialVersionUID = -1509801250911366337L;
	private String message;

	public ResourceNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
		
}
