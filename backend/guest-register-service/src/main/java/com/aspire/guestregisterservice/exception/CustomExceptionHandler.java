package com.aspire.guestregisterservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aspire.guestregisterservice.model.DataInvalid;
import com.aspire.guestregisterservice.model.GuestException;

/**
 * Class to handle the messages generated during the validation process.
 * @author Mildred Zaragoza
 *
 */

@RestControllerAdvice
public class CustomExceptionHandler {
	
	/**
	 * This method handles GuestNotFoundException and assigns an HttpStatus code.
	 * @param exception
	 * @return GuestException 
	 */
	@ExceptionHandler(GuestNotFoundException.class)
	public ResponseEntity<Object> handleGuestNotFoundException(GuestNotFoundException exception){
		GuestException guestException = new GuestException(exception.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(guestException, HttpStatus.NOT_FOUND);
	}
	

	/**
	 * This method links the field's name and its error message
	 * @param MethodArgumentNotValidException exception
	 */
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleValidateExceptions(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<String, String>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		DataInvalid dataInvalid = new DataInvalid(errors.toString(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(dataInvalid, HttpStatus.BAD_REQUEST);
	}
}
