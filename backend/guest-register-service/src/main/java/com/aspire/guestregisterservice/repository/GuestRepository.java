package com.aspire.guestregisterservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aspire.guestregisterservice.model.Guest;


/**
 * This interface extends JpaRepository to implement the methods available in the CRUD Repository
 * @author Mildred Zaragoza
 *
 */

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
