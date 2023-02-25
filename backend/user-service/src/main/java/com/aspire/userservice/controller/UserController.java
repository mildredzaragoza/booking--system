package com.aspire.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.userservice.model.User;
import com.aspire.userservice.service.UserService;
import com.aspire.userservice.utils.exception.DataInvalidException;
import com.aspire.userservice.utils.exception.UserNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Class to define the endpoints of the Users API
 * @author Mildred Zaragoza
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
    @ApiOperation(value = "Update user's password", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "400", description = "Username and 5 digits password are required"),
            @ApiResponse(responseCode = "404", description = "User to update not found"),
    })
    @PutMapping("/{username}")
    public ResponseEntity<Object> updatePassword(@PathVariable String username, @RequestBody String password){ 
        try {
        	return new ResponseEntity<>(userService.updatePassword(username, password), HttpStatus.OK);
		}catch(DataInvalidException exception) {
			throw new DataInvalidException(exception.getMessage());
    	}catch (UserNotFoundException exception) {
			throw new UserNotFoundException(exception.getMessage());
		} 
    }
    
    @ApiOperation(value = "Get users")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description ="Users found"),
    		@ApiResponse(responseCode = "500", description = "Something went wrong")
    })
    @GetMapping()
    public ResponseEntity<Object> getUsers(){
    	try {
    		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    	}catch (UserNotFoundException exception) {
    		throw new UserNotFoundException(exception.getMessage());
    	}
    }
}
