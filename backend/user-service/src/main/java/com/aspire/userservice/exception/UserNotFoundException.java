package com.aspire.userservice.exception;


/**
 * Custom exception to throw when a user is not found.
 * @author Mildred Zaragoza
 *
 */
public class UserNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}