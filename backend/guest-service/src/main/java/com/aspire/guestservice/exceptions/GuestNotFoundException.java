package com.aspire.guestservice.exceptions;

public class GuestNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuestNotFoundException(String message) {
		super(message);
	}

}
