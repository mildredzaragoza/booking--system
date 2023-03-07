package com.aspire.userservice.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aspire.userservice.model.User;
import com.aspire.userservice.repository.UserRepository;
import com.aspire.userservice.utils.exception.DataInvalidException;
import com.aspire.userservice.utils.exception.UserNotFoundException;
import com.aspire.userservice.utils.validator.UserValidator;

/**
 * Class to implement methods available on UserRepository such as 
 * users and undatePassword.
 * @author Mildred Zaragoza
 *
 */


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserValidator userValidator;
	
	/**
	 * This method updates the user's password. 
	 * @param username
	 * @param password: new password
	 * @return the user updated
	 */
    public ResponseEntity<Object> updatePassword(String username, String password) {
		try{
			userValidator.validate(password);
			User userToUpdate = userRepository.findByUsername(username).get();
	    	userToUpdate.setPassword(password);
	    	return new ResponseEntity<>(userRepository.save(userToUpdate), HttpStatus.OK);
		}catch(DataInvalidException exception) {
			throw new DataInvalidException(exception.getMessage());
    	}catch(NoSuchElementException exception) {
			throw new UserNotFoundException("User to update not found.");
    	}catch(IllegalArgumentException exception) {
    		return new ResponseEntity<>("Provide valid user's password.", HttpStatus.BAD_REQUEST);
		} 
    }
    
    /**
	 * This method returns a user by its username
	 * @return the user found
	 */
    public ResponseEntity<Object> getUsers(){
    	if(!userRepository.findAll().isEmpty()) {
    		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    	}else {
    		throw new UserNotFoundException("The list of users is empty.");
    	}
    }
    
    /**
	 * This method returns a user's information identified by its username
	 * @param username must not be null
	 * @return Response with the information in the body
	 */
    public ResponseEntity<Object> getUserByUsername(String username) {
    	try {
    		return new ResponseEntity<>(userRepository.findByUsername(username).get(), HttpStatus.OK);
    	}catch(IllegalArgumentException exception ) {
          	return new ResponseEntity<>("Provide a valid username", HttpStatus.BAD_REQUEST);
    	}catch(NoSuchElementException exception) {
    		throw new UserNotFoundException("User not found.");
    	}
    }
}
