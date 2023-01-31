package com.cerner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * Exception class to handle cases where a record was not found. 
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructs a new RecordNotFoundException with the specified detail message.
	 * 
	 * @param exception the detail message
	 */
	public RecordNotFoundException(String exception) {
		super(exception);
	}

}