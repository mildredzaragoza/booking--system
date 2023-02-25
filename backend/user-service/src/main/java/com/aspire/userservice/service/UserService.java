package com.aspire.userservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * @throws UserNotFoundException if the user with the specified id was not found.
	 * @throws IllegalArgumentException - if the password does not have at least 5 digits.
	 */
    public User updatePassword(String username, String password) {
		try{
			userValidator.validate(password);
			User userToUpdate = userRepository.findByUsername(username).get();
	    	userToUpdate.setPassword(password);
	    	return userRepository.save(userToUpdate);
		}catch(DataInvalidException exception) {
			throw new DataInvalidException(exception.getMessage());
    	}catch(NoSuchElementException exception) {
			throw new UserNotFoundException("User to update not found.");
		}catch(IllegalArgumentException exception) {
			throw new IllegalArgumentException("Provide valid user password.");
    	}  
    }
    
    /**
	 * This method returns all users stored.
	 * @return a list of users' information
	 * @throws Exception - if the list of users is empty.
	 */
    public List<User> getUsers(){
    	if(!userRepository.findAll().isEmpty()) {
    		return  userRepository.findAll();
    	}else {
    		throw new UserNotFoundException("The list of users is empty.");
    	}
    }
}
