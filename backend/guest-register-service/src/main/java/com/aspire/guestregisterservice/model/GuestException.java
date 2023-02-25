package com.aspire.guestregisterservice.model;

import org.springframework.http.HttpStatus;

public class GuestException {
	private String message;
	private HttpStatus httpStatus; 
	
	public GuestException(String message, HttpStatus http) {
		this.httpStatus = http;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
}
