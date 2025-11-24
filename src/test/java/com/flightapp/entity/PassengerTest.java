package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.flightapp.entity.enums.Gender;

class PassengerTest {
	@Test
    void testPassengerEntity() {
        Passenger p = new Passenger();
        p.setId("P1");
        p.setName("John");
        p.setGender(Gender.MALE);
        p.setAge(25);
        p.setEmail("john@gmail.com");
        p.setBookingId("B1");
        assertEquals("P1", p.getId());
        assertEquals("John", p.getName());
        assertEquals(Gender.MALE, p.getGender());
        assertEquals(25, p.getAge());
        assertEquals("john@gmail.com", p.getEmail());
        assertEquals("B1", p.getBookingId());
    }
}
