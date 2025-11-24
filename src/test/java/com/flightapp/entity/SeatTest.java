package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SeatTest {
	@Test
    void testSeatEntity() {
        Seat s = new Seat();
        s.setSeatNumber("S1");
        s.setAvailable(true);
        assertEquals("S1", s.getSeatNumber());
        assertTrue(s.isAvailable());
    }
}
