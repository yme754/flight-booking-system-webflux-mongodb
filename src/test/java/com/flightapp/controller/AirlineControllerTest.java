package com.flightapp.controller;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.flightapp.entity.Airline;
import com.flightapp.entity.Flight;
import com.flightapp.service.AirlineService;
import com.flightapp.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(AirlineController.class)
public class AirlineControllerTest {
	@Autowired
    private WebTestClient webClient;
    @MockBean
    private AirlineService airlineService;
    @MockBean
    private FlightService flightService;
    
    @Test
    void testGetInventory() {
        Flight f = new Flight();
        f.setId("F1");
        f.setAirlineId("A1");
        f.setFromPlace("HYD");
        f.setToPlace("BLR");
        f.setAvailableSeats(10);
        f.setSeats(List.of("S1","S2"));
        when(flightService.getAllFlights()).thenReturn(Flux.just(f));
        webClient.get()
                .uri("/api/flight/airline/inventory")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Map.class)
                .hasSize(1);
    }

    @Test
    void testAddAirline() {
        Airline a = new Airline("A1", "Indigo", "logo.png");
        when(airlineService.addAirline(any(Airline.class)))
                .thenReturn(Mono.just(a));
        webClient.post()
                .uri("/api/flight/airline/add")
                .bodyValue(a)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Airline.class);
    }
    
    @Test
    void testAddInventory() {
        Flight f = new Flight();
        f.setId("F1");
        f.setAirlineId("A1");
        when(flightService.addFlightInventory(any(Flight.class)))
                .thenReturn(Mono.just(f));
        webClient.post()
                .uri("/api/flight/airline/inventory/add").bodyValue(f)
                .exchange().expectStatus().isCreated().expectBody(Map.class)
                .value(map -> map.containsKey("id"));
    }
}
