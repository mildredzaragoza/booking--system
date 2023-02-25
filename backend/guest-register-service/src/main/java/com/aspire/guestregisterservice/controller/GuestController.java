package com.aspire.guestregisterservice.controller;

import com.aspire.guestregisterservice.exception.DataInvalidException;
import com.aspire.guestregisterservice.exception.GuestNotFoundException;
import com.aspire.guestregisterservice.model.Guest;
import com.aspire.guestregisterservice.model.Response;
import com.aspire.guestregisterservice.service.GuestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


/**
 * Class to define the endpoint of the Guests API
 * @author Mildred Zaragoza
 *
 */

@RestController
@RequestMapping("/guests")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @ApiOperation(value = "Gets all guests registered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guests found"),
            @ApiResponse(responseCode = "404", description = "There are no guests"),
    })
    @GetMapping
    public Response guests(){
    	return guestService.guests();
    }

    @ApiOperation(value = "Saves new guest", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest was saved successfully"),
            @ApiResponse(responseCode = "400", description = "Guest's data is incompleted")
    })
    @PostMapping
    public ResponseEntity<Guest> saveGuest(@RequestBody @Valid Guest newGuest){
        try {
			return new ResponseEntity<Guest>(guestService.saveGuest(newGuest), HttpStatus.OK);
		}catch(Exception exception) { 
			throw new DataInvalidException(exception.getMessage());
    	}
    }

    @ApiOperation(value = "Finds guest by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest found"),
            @ApiResponse(responseCode = "404", description = "Guest not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> guestById(@Parameter(description = "id of guest to be searched") @PathVariable Long id){
        try{
        	return new ResponseEntity<>(guestService.guestById(id), HttpStatus.OK);
        }catch(GuestNotFoundException exception){
        	throw new GuestNotFoundException(exception.getMessage());
        }
    }

    @ApiOperation(value = "Updates a guest searching by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Guest's data is incomplete"),
            @ApiResponse(responseCode = "404", description = "Guest to update not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGuest(@Parameter(description = "id of guest to be updated") @PathVariable Long id, @RequestBody @Valid Guest guest) {
        try{
            return new ResponseEntity<>(guestService.updateGuest(id, guest), HttpStatus.OK);
        }catch(GuestNotFoundException exception){
        	throw new GuestNotFoundException(exception.getMessage());
        }catch(Exception exception) { 
        	throw new DataInvalidException(exception.getMessage());
        }
    }

    @ApiOperation(value = "Deletes guest by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guest deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Guest to delete not found"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGuest(@Parameter(description = "id of guest to be deleted") @PathVariable Long id){
    	try {
    		return new ResponseEntity<>(guestService.deleteGuest(id), HttpStatus.OK);
    	}catch(GuestNotFoundException exception) {
    		throw new GuestNotFoundException(exception.getMessage());
    	}
    }
}
