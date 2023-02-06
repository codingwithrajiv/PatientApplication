package com.cerner.exception;

public class ErrorResponse {

	private int status;

	private String message;

	public ErrorResponse() {
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
