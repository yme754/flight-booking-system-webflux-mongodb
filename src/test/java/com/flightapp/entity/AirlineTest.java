package com.flightapp.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AirlineTest {
	@Test
    void testAirlineEntity() {
        Airline a = new Airline();
        a.setId("A1");
        a.setName("Indigo");
        a.setLogoUrl("logo.png");
        assertEquals("A1", a.getId());
        assertEquals("Indigo", a.getName());
        assertEquals("logo.png", a.getLogoUrl());
        Airline a2 = new Airline("A2", "Vistara", "v.png");
        assertEquals("A2", a2.getId());
	}
}