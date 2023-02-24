package com.aspire.guestservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspire.guestservice.exceptions.GuestNotFoundException;
import com.aspire.guestservice.models.Guest;
import com.aspire.guestservice.models.GuestInput;
import com.aspire.guestservice.repository.GuestRepository;

/**
 * Class to implement methods available on GuestRepository such as 
 * guests, guestById, addGuest, updateGuest and deleteGuest.
 * @author Mildred Zaragoza
 *
 */
@Service
public class GuestService {
	@Autowired
	private GuestRepository guestRepository;
	
	/**
	 * This method returns all guests stored.
	 * @return a list of guests' information
	 */
	public List<Guest> guests(){
		return (ArrayList<Guest>)guestRepository.findAll();
	}
	
	/**
	 * This method returns a guest's information identified by its id
	 * @param id must not be null
	 * @return the entity with the given id if exists
	 * @throws GuestNotFoundException - if the guest with the specified id was not found.
	 */
	public Guest guestById(Long id) {
		try {
			return guestRepository.findById(id).get();
		}catch(NoSuchElementException exception) {
			throw new GuestNotFoundException("Guest not found.");
		}
		
	}
	
	/**
	 * This method saves a new guest in the current database.
	 * @param guest must not be null 
	 * @return the guest saved
	 */
	public Guest addGuest(GuestInput guest) {
		Guest newGuest = new Guest();
		newGuest.setName(guest.name());
		newGuest.setEmail(guest.email());
		newGuest.setCheckInDate(guest.checkInDate());
		newGuest.setCheckOutDate(guest.checkOutDate());
		newGuest.setPhoneNumber(guest.phoneNumber());
		newGuest.setTypeGuest(guest.typeGuest()); 
		return guestRepository.save(newGuest);
	}
	
	/**
	 * This method updates a guest already stored in the database.
	 * @param guest's id
	 * @param guest's information
	 * @return the guest updated
	 * @throws GuestNotFoundException - if the guest with the specified id was not found.
	 */
	public Guest updateGuest(Long id, GuestInput guest) {
		try {
			Guest guestToUpdate = guestRepository.findById(id).get();
			guestToUpdate.setName(guest.name());
			guestToUpdate.setEmail(guest.email());
			guestToUpdate.setCheckInDate(guest.checkInDate());
			guestToUpdate.setCheckOutDate(guest.checkOutDate());
			guestToUpdate.setPhoneNumber(guest.phoneNumber());
			guestToUpdate.setTypeGuest(guest.typeGuest()); 
			guestToUpdate.setIdGuest(id);
		}catch(NoSuchElementException exception) {
			throw new GuestNotFoundException("Guest to update not found");
		}
		return null;
	}
	
	/**
	 * This method deletes a guest identified by its id
	 * @param id must not be null
	 * @return the list of current guests
	 * @throws GuestNotFoundException - if the guest with the specified id was not found.
	 */
	public List<Guest> deleteGuest(Long id){
		try {
			Guest guestToDelete = guestRepository.findById(id).get();
			guestRepository.delete(guestToDelete);
			return (ArrayList<Guest>)guestRepository.findAll();
		}catch(NoSuchElementException exception) {
			throw new GuestNotFoundException("Guest to delete not found");
		}
	}
}
