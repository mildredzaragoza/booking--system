package com.aspire.guestregisterservice.service;

import com.aspire.guestregisterservice.exception.GuestNotFoundException;
import com.aspire.guestregisterservice.model.Guest;
import com.aspire.guestregisterservice.model.InfoResult;
import com.aspire.guestregisterservice.model.Response;
import com.aspire.guestregisterservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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
    
    private Response response;
    private InfoResult info;

	/**
	 * This method saves a new guest in the current database.
	 * @param guest must not be null 
	 * @return the guest saved
	 * @throws IllegalArgumentException - if the data is provided in a wrong way
	 */
    public Guest saveGuest(Guest guest) {
    	try {
    		return guestRepository.save(guest);
    	}catch(IllegalArgumentException exception) {
    		throw new IllegalArgumentException("Provide valid guest information.");
    	}  
    }
    
    
	/**
	 * This method returns all guests stored.
	 * @return a response with info and the list of guests
	 */
    public Response guests() {
    	if(!guestRepository.findAll().isEmpty()) {
    		info.setHttpStatus(HttpStatus.OK);
    		info.setMessage(guestRepository.findAll().size() + " guests found");
    		response.setInfo(info);
    		response.setResult((ArrayList<Guest>)guestRepository.findAll());
    		return response;
    	}else {
    		info.setHttpStatus(HttpStatus.NOT_FOUND);
    		info.setMessage("The list of guests is empty");
    		response.setInfo(info);
    		response.setResult(null);
    		return response;
    	}
    		
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
     	   throw new GuestNotFoundException("Guest with ID " + id + " doesn't exist");
        }
     }

    /**
	 * This method updates a guest already stored in the database.
	 * @param guest's id
	 * @param guest's information
	 * @return the guest updated
	 * @throws NoSuchElementException - if the guest with the specified id was not found.
	 * @throws IllegalArgumentException - if the data is provided in a wrong way
	 */
    public Guest updateGuest(Long id, Guest guest) {
    	try {
    		Guest guestToUpdate = guestRepository.findById(id).get();
            guestToUpdate.setName(guest.getName());
            guestToUpdate.setEmail(guest.getEmail());
            guestToUpdate.setPhoneNumber(guest.getPhoneNumber());
            guestToUpdate.setTypeGuest(guest.getTypeGuest());
            guestToUpdate.setCheckInDate(guest.getCheckInDate());
            guestToUpdate.setCheckOutDate(guest.getCheckOutDate());
            return guestRepository.save(guestToUpdate);
    	}catch(NoSuchElementException exception) {
    		throw new GuestNotFoundException("Guest to update doesn't exist.");
    	}catch(IllegalArgumentException exception) {
    		throw new IllegalArgumentException("Provide valid guest information.");
    	} 
    }

    /**
	 * This method deletes a guest identified by its id
	 * @param id must not be null
	 * @return the list of current guests
	 * @throws GuestNotFoundException - if the guest with the specified id was not found.
	 */
    public ArrayList<Guest> deleteGuest(Long id){
    	try {
			Guest guestToDelete = guestRepository.findById(id).get();
			guestRepository.delete(guestToDelete);
			return (ArrayList<Guest>)guestRepository.findAll();
		}catch(NoSuchElementException exception) {
			throw new GuestNotFoundException("Guest to delete not found");
		}
    }


}
