package com.aspire.userservice.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to throw when the guest's information
 * provides is not completed or in a wrong format.
 * @author Mildred Zaragoza
 *
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataInvalidException(String message){
        super(message);
    }
}
