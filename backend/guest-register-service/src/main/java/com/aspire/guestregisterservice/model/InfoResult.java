package com.aspire.guestregisterservice.model;

import org.springframework.http.HttpStatus;

public class InfoResult {
	private HttpStatus httpStatus;
	private String message;
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InfoResult(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}
	
	
}
