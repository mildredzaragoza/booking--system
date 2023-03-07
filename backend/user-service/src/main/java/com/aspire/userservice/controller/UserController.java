package com.aspire.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.userservice.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
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
	
    @ApiOperation(value = "Update user's password", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request resolved successfully")
    })
    @PutMapping("/{username}")
    public ResponseEntity<Object> updatePassword(@PathVariable String username, @RequestBody String password){ 
        return userService.updatePassword(username, password);
    }
    
    @ApiOperation(value = "Get users", response = ResponseEntity.class)
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description ="Request resolved successfully")
    })
    @GetMapping()
    public ResponseEntity<Object> getUsers(){
    	return userService.getUsers();
    }
    
    @ApiOperation(value = "Finds user by username", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request resolved successfully")
    })
    @GetMapping("/{username}")
    public ResponseEntity<Object> guestByUsername(@Parameter(description = "User's username") @PathVariable String username){
        return userService.getUserByUsername(username);
    }
    
    
}
