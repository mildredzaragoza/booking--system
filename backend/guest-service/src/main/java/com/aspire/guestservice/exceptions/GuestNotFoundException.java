package com.aspire.guestservice.exceptions;


/**
 * Custom exception to throw when a guest is not found.
 * @author Mildred Zaragoza
 *
 */
public class GuestNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuestNotFoundException(String message) {
		super(message);
	}

}
