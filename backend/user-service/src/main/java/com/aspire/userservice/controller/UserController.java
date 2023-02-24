package com.aspire.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.aspire.userservice.model.User;
import com.aspire.userservice.service.UserService;

/**
 * Class to define the endpoint of the Users API
 * @author Mildred Zaragoza
 *
 */

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@MutationMapping
	public User updatePassword(@Argument String username, @Argument String password) throws Exception{ 
		try {
			return userService.updatePassword(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
    }
	
	@QueryMapping
    public List<User> users(){
    	return userService.users();
    }
}
