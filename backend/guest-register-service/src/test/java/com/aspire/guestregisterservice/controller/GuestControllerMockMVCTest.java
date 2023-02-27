package com.aspire.guestregisterservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.ResponseEntity;

import com.aspire.guestregisterservice.model.Guest;
import com.aspire.guestregisterservice.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(GuestController.class)
public class GuestControllerMockMVCTest {

    @Autowired
    private MockMvc mockMvc;
    
	@MockBean
    private GuestService guestService;
	
	@Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @DisplayName("Guet all guests test")
    public void getAllGuestTest() throws Exception {
    	when(guestService.guests()).thenReturn(new ResponseEntity<>(new ArrayList<Guest>(), HttpStatus.OK));
    	mockMvc.perform(MockMvcRequestBuilders.get("/guests").contentType(MediaType.APPLICATION_JSON))
    		   .andExpect(MockMvcResultMatchers.status().isOk());
    	verify(guestService).guests();
    }
    
    @Test
    @DisplayName("Get guest by id test")
    public void getGuestByIdTest() throws Exception {
    	long guestId = 5L;
    	Guest demoGuest = new Guest();
    	when(guestService.guestById(guestId)).thenReturn(new ResponseEntity<>(demoGuest, HttpStatus.OK));
    	mockMvc.perform(MockMvcRequestBuilders.get("/guests/{id}", guestId).contentType(MediaType.APPLICATION_JSON))
    		   .andExpect(MockMvcResultMatchers.status().isOk());
    	verify(guestService).guestById(guestId);
    }
    
    @Test
    @DisplayName("Get guest by invalid id test")
    public void getGuestByInvalidIdTest() throws Exception {
    	long guestId = 500L;
    	when(guestService.guestById(guestId)).thenReturn(null);
    	mockMvc.perform(MockMvcRequestBuilders.get("/guests/{id}", guestId).contentType(MediaType.APPLICATION_JSON))
    		   .andExpect(MockMvcResultMatchers.status().isNotFound());
    	verify(guestService).guestById(guestId);
    }
    
    @Test
    @DisplayName("Delete guest by id test")
    public void deteleGuestTest() throws Exception{
    	when(guestService.deleteGuest(1L)).thenReturn(new ResponseEntity<>(new ArrayList<Guest>(), HttpStatus.OK));
    	mockMvc.perform(MockMvcRequestBuilders.delete("/guests/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
    		   .andExpect(MockMvcResultMatchers.status().isOk());
    	verify(guestService).deleteGuest(1L);
    }
    
    @Test
    @DisplayName("Delete guest by invalid id test")
    public void deleteGuestWithInvalidIdTest() throws Exception {
    	when(guestService.deleteGuest(500L)).thenReturn(new ResponseEntity<>(new ArrayList<Guest>(), HttpStatus.OK));
    	mockMvc.perform(MockMvcRequestBuilders.delete("/guests/{id}", 500L).contentType(MediaType.APPLICATION_JSON))
    		   .andExpect(MockMvcResultMatchers.status().isNotFound());
    	verify(guestService).deleteGuest(500L);
    }
    
    @Test
    @DisplayName("Save new guest test")
    public void saveGuestTest() throws Exception {
    	Guest demoGuest = new Guest();
    	demoGuest.setName("Demo Guest");
    	demoGuest.setEmail("demo@demo.com");
    	when(guestService.saveGuest(any(Guest.class))).thenReturn(new ResponseEntity<>(demoGuest, HttpStatus.OK));
    	mockMvc.perform(MockMvcRequestBuilders.post("/guests")
    			.content(objectMapper.writeValueAsString(new Guest()))
    			.contentType(MediaType.APPLICATION_JSON))
    		   .andExpect(MockMvcResultMatchers.status().isOk())
    		   .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    @DisplayName("Update guest test")
    public void updateGuestTest() throws Exception {
    	Guest newGuest = new Guest();
    	newGuest.setName("Demo guest updated");
    	newGuest.setEmail("demoupdatedguest@demo.com");
    	when(guestService.updateGuest(1L, any(Guest.class))).thenReturn(new ResponseEntity<>(newGuest, HttpStatus.OK));
    	mockMvc.perform(MockMvcRequestBuilders.put("/guests/{id}", 1L)
    			.content(objectMapper.writeValueAsString(newGuest))
    			.contentType(MediaType.APPLICATION_JSON))
    			.andExpect(MockMvcResultMatchers.status().isOk())
    			.andDo(MockMvcResultHandlers.print());
    	
    }
    
}
