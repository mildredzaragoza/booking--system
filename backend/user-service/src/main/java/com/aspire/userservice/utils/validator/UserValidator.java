package com.aspire.userservice.utils.validator;

import org.springframework.stereotype.Service;

import com.aspire.userservice.utils.exception.DataInvalidException;

/**
 * Class to validate user's password and username.
 * @author Mildred Zaragoza
 *
 */

@Service
public class UserValidator {
	public void validate(String password) throws DataInvalidException {
      /*  if(username() == null || user.getUsername().isEmpty()){
            setMessage("Username is required.");
        }
        */
        if(password == null || password.isEmpty()) {
        	setMessage("Password is required");
        }else if (password.length() < 5) {
            setMessage("Your password must contain at least 5 digits.");
        }
    }

    private void setMessage(String message) throws DataInvalidException{
        throw new DataInvalidException(message);
    }
}
