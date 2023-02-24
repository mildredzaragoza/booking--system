package com.aspire.userservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.aspire.userservice.model.User;
import com.aspire.userservice.repository.UserRepository;

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
	
	/**
	 * This method updates the user's password. 
	 * @param username
	 * @param password: new password
	 * @return the user updated
	 */
    public User updatePassword(String username, String password) throws Exception {
		try{
			User userToUpdate = userRepository.findByUsername(username).get();
	    	userToUpdate.setPassword(password);
	    	return userRepository.save(userToUpdate);
		}catch(NoSuchElementException exception) {
			throw new NoSuchElementException("User to update not found.");
		}
    }
    
    
    /**
	 * This method returns all users stored.
	 * @return a list of users' information
	 */
    public List<User> users(){
    	return (ArrayList<User>)userRepository.findAll();
    }
}
