package com.aspire.guestregisterservice.controller;

import com.aspire.guestregisterservice.model.Guest;
import com.aspire.guestregisterservice.service.GuestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            @ApiResponse(responseCode = "200", description = "Request resolved successfully")
    })
    @GetMapping
    public ResponseEntity<Object> guests(){
    	return guestService.guests();
    }

    @ApiOperation(value = "Saves new guest", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request resolved successfully")
    })
    @PostMapping
    public ResponseEntity<Object> saveGuest(@RequestBody @Valid Guest newGuest){
        return guestService.saveGuest(newGuest);
    }

    @ApiOperation(value = "Finds guest by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request resolved successfully")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> guestById(@Parameter(description = "id of guest to be searched") @PathVariable Long id){
        return guestService.guestById(id);
    }

    @ApiOperation(value = "Updates a guest searching by id", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request resolved successfully")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGuest(@Parameter(description = "id of guest to be updated") @PathVariable Long id, @RequestBody @Valid Guest guest) {
        return guestService.updateGuest(id, guest);
    }

    @ApiOperation(value = "Deletes guest by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request resolved successfully")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGuest(@Parameter(description = "id of guest to be deleted") @PathVariable Long id){
    	return guestService.deleteGuest(id);
    }
}
