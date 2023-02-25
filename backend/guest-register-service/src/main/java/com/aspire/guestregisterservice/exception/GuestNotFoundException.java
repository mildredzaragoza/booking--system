package com.aspire.guestregisterservice.exception;

/**
 * Custom exception to throw when a user is not found.
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
