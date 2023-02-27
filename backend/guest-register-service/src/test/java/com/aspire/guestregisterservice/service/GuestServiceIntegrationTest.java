package com.aspire.guestregisterservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import com.aspire.guestregisterservice.model.Guest;

@SpringBootTest
@DisplayName("Test service")
class GuestServiceIntegrationTest {
	@Autowired
	private GuestService guestService;
	
	@Test
	@DisplayName("Test save guest")
	public void saveGuestTest() throws Exception {
		Guest guestOne = new Guest();
		guestOne.setName("Test one");
		guestOne.setEmail("test1@test.com");
		guestOne.setPhoneNumber("1523689715");
		guestOne.setTypeGuest("basic");
		guestOne.setCheckInDate("2022-08-09");
		guestOne.setCheckOutDate("2022-09-09");
		guestService.saveGuest(guestOne);
		assertTrue(guestOne.getId() != null);
	}
	
	
	@Test
	@DisplayName("Test save guest missing data")
	public void saveGuestTestMissingData() throws Exception {
		assertThrows(DataIntegrityViolationException.class, () -> guestService.saveGuest(new Guest()));
	}
	
	
	@Test
	@DisplayName("Test display all guests")
	public void getAllGuestTest() throws Exception {
		ResponseEntity<Object> guests = guestService.guests();
		assertTrue(guests.getBody() !=  null);
	}
	
	@Test
	@DisplayName("Test get guest by id")
	public void getGuestByIdTest() throws Exception {
		ResponseEntity<Object> guest = guestService.guestById(5L);
		assertTrue(guest.getBody() != null);
	}
	
	@Test
	@DisplayName("Test get guest by not existing id")
	public void getGuestByNoExistIdTest() throws Exception {
		assertThrows(NoSuchElementException.class, () -> guestService.guestById(80L));
	}	
	
	@Test
	@DisplayName("Test update guest")
	public void updateGuestTest() throws Exception {
		Guest guest = new Guest();
		guest.setName("Test one updated");
		guest.setEmail("test1@test.com");
		guest.setPhoneNumber("1523689715");
		guest.setTypeGuest("plus");
		guest.setCheckInDate("2022-08-09");
		guest.setCheckOutDate("2022-08-09");
		assertNotNull(guestService.updateGuest(5L, guest));
	}
	
	@Test
	@DisplayName("Test update guest missing data")
	public void updateGuestTestMissingData() throws Exception {
		assertThrows(DataIntegrityViolationException.class, () -> guestService.updateGuest(1L, new Guest())); 
	}
	
	@Test
	@DisplayName("Test update guest with not existing id")
	public void updateGuestTestIdNotExists() throws Exception {
		assertThrows(NoSuchElementException.class, () -> guestService.updateGuest(80L, new Guest())); 
	}
	
	@Test 
	@DisplayName("Test delete guest")
	public void testDeleteGuest() throws Exception {
		ResponseEntity<Object> guests = guestService.deleteGuest(43L);
		assertTrue(guests.getBody() != null);
	}
	
	@Test 
	@DisplayName("Test delete guest with id doesn't exist")
	public void testDeleteGuestNotExistingId() throws Exception {
		assertThrows(NoSuchElementException.class, () -> guestService.deleteGuest(80L)); 
		
	}
}
