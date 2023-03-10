package com.aspire.userservice.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aspire.userservice.model.User;
import com.aspire.userservice.utils.exception.DataInvalidException;
import com.aspire.userservice.utils.exception.UserNotFoundException;


@SpringBootTest
@DisplayName("Test user service")
class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;

	@Test
	@DisplayName("Test update user's password")
	public void updateUserPasswordTest() throws Exception {
		User user = new User("dev", "12345");
		assertNotNull(userService.updatePassword(user.getUsername(), user.getPassword())); 
	} 
	
	@Test
	@DisplayName("Test update user's password with invalid password")
	public void updateInvalidUserPasswordTest() throws Exception {
		User user = new User("dev", "123");
		assertThrows(DataInvalidException.class, () -> userService.updatePassword(user.getUsername(), user.getPassword())); 
	}
	
	@Test
	@DisplayName("Test update user's password with no existing username")
	public void updateUserPasswordInvalidUsernameTest() throws Exception {
		User user = new User("devvv", "12345");
		assertThrows(UserNotFoundException.class, () -> userService.updatePassword(user.getUsername(), user.getPassword())); 
	}
	
	@Test
	@DisplayName("Test update user's password with missing data")
	public void updateUserPasswordMissingDataTest() throws Exception {
		assertThrows(DataInvalidException.class, () -> userService.updatePassword("","")); 
	}
}
