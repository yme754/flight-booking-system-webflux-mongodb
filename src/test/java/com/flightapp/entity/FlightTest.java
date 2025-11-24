package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FlightTest {
	@Test
    void testFlightEntity() {
        Flight f = new Flight();
        f.setId("F1");
        f.setAirlineId("A1");
        f.setFromPlace("HYD");
        f.setToPlace("BLR");
        f.setDepartureTime(LocalDateTime.now());
        f.setAvailableSeats(20);
        f.setSeats(List.of("S1", "S2"));
        assertEquals("F1", f.getId());
        assertEquals("A1", f.getAirlineId());
        assertEquals("HYD", f.getFromPlace());
        assertEquals(20, f.getAvailableSeats());
        assertEquals(2, f.getSeats().size());
    }
}
