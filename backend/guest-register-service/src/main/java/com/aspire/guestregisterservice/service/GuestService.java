package com.aspire.guestregisterservice.service;

import com.aspire.guestregisterservice.exception.GuestNotFoundException;
import com.aspire.guestregisterservice.model.Guest;
import com.aspire.guestregisterservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	/**
	 * This method saves a new guest in the current database.
	 * @param guest must not be null 
	 * @return Response with the information in the body
	 */
    public ResponseEntity<Object> saveGuest(Guest guest) {
    	try {
    		return new ResponseEntity<>(guestRepository.save(guest), HttpStatus.OK);
    	}catch(IllegalArgumentException exception) {
    		return new ResponseEntity<>("Provide valid information", HttpStatus.BAD_REQUEST);
    	}  
    }
    
    
	/**
	 * This method returns all guests stored.
	 * @return Response with the information in the body
	 */
    public ResponseEntity<Object> guests() {
    	if(!guestRepository.findAll().isEmpty()) {
    		return new ResponseEntity<>((ArrayList<Guest>)guestRepository.findAll(), HttpStatus.OK);
    	}else {
    		throw new GuestNotFoundException("The list of guests is empty.");
    	}
    		
    }

    /**
	 * This method returns a guest's information identified by its id
	 * @param id must not be null
	 * @return Response with the information in the body
	 */
    public ResponseEntity<Object> guestById(Long id) {
        try {
        	Guest guest = guestRepository.findById(id).get();
    		return new ResponseEntity<>(guest, HttpStatus.OK);
        }catch(IllegalArgumentException exception ) {
        	return new ResponseEntity<>("Provide a valid id", HttpStatus.BAD_REQUEST);
    	}catch(NoSuchElementException exception) {
    		throw new GuestNotFoundException("Guest with id: " + id + " not found");
        }
     }

    /**
	 * This method updates a guest already stored in the database.
	 * @param guest's id
	 * @param guest's information
	 * @return Response with the information in the body
	 */
    public ResponseEntity<Object> updateGuest(Long id, Guest guest) {
    	try {
    		Guest guestToUpdate = guestRepository.findById(id).get();
            guestToUpdate.setName(guest.getName());
            guestToUpdate.setEmail(guest.getEmail());
            guestToUpdate.setPhoneNumber(guest.getPhoneNumber());
            guestToUpdate.setTypeGuest(guest.getTypeGuest());
            guestToUpdate.setCheckInDate(guest.getCheckInDate());
            guestToUpdate.setCheckOutDate(guest.getCheckOutDate()); 
        	return new ResponseEntity<>(guestRepository.save(guestToUpdate), HttpStatus.OK);
    	}catch(IllegalArgumentException exception) {
    		return new ResponseEntity<>("Provide valid information", HttpStatus.BAD_REQUEST);
    	}catch(NoSuchElementException exception) {
    		throw new GuestNotFoundException("Guest with id: " + id + " not found");
    	} 
    }

    /**
	 * This method deletes a guest identified by its id
	 * @param id must not be null
	 * @return Response with the information in the body
	 */
    public ResponseEntity<Object> deleteGuest(Long id){
    	try {
			Guest guestToDelete = guestRepository.findById(id).get();
			guestRepository.delete(guestToDelete);
			return new ResponseEntity<>(true, HttpStatus.OK);
		}catch(NoSuchElementException exception) {
			throw new GuestNotFoundException("Guest with id: " + id + " not found");
		}
    }


}
