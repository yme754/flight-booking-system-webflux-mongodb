package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BookingTest {
	@Test
    void testBookingEntity() {
        Booking b = new Booking();
        b.setId("B1");
        b.setPnr("PNR123");
        b.setFlightId("F1");
        b.setAirlineId("A1");
        b.setEmail("test@gmail.com");
        b.setSeatCount(2);
        b.setSeatNumbers(List.of("S1", "S2"));
        b.setPassengers(List.of(new Passenger()));
        b.setCreatedAt(LocalDateTime.now());
        assertEquals("B1", b.getId());
        assertEquals("PNR123", b.getPnr());
        assertEquals("F1", b.getFlightId());
        assertEquals(2, b.getSeatCount());
        assertEquals(2, b.getSeatNumbers().size());
    }
}
