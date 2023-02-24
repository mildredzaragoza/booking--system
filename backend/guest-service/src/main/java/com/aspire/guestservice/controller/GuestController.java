package com.aspire.guestservice.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.aspire.guestservice.exceptions.GuestNotFoundException;
import com.aspire.guestservice.models.Guest;
import com.aspire.guestservice.models.GuestInput;
import com.aspire.guestservice.service.GuestService;



/**
 * Class to define the endpoint of the Guests API
 * @author Mildred Zaragoza
 *
 */

@Controller
public class GuestController {
	@Autowired
	private GuestService guestService;
	
	@QueryMapping
	public List<Guest> guests(){
		return guestService.guests();
	}
	
	@QueryMapping
	public Guest guestById(@Argument Long id){
		try {
			return guestService.guestById(id);
		}catch(GuestNotFoundException exception) {
			throw new GuestNotFoundException(exception.getMessage());
		}
		
	}
	
	@MutationMapping
	public Guest addGuest(@Argument GuestInput guest) {
		return guestService.addGuest(guest);
	}
	
	@MutationMapping
	public Guest updateGuest(@Argument Long id, @Argument GuestInput guest) {
		try{
			return guestService.updateGuest(id, guest);
		}catch(GuestNotFoundException exception) {
			throw new GuestNotFoundException(exception.getMessage());
		}
	}
	
	@MutationMapping
	public List<Guest> deleteGuest(@Argument Long id){
		try {
			return guestService.deleteGuest(id);
		}catch(GuestNotFoundException exception) {
			throw new GuestNotFoundException(exception.getMessage());
		}
		
	}
}
