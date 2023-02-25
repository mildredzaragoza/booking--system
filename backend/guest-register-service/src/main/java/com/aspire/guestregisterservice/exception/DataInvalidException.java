package com.aspire.guestregisterservice.exception;

/**
 * Custom exception to throw when the data does not have the correct format.
 * @author Mildred Zaragoza
 *
 */
public class DataInvalidException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public DataInvalidException(String message) {
		super(message);
	}
}
