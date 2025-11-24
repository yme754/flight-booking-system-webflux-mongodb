package com.flightapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.flightapp.entity.Passenger;
import com.flightapp.service.PassengerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PassengerControllerTest {
	@Autowired
    private WebTestClient webClient;
    @MockBean
    private PassengerService passengerService;

    @Test
    void testAddPassenger() {
        Passenger p = new Passenger();
        p.setId("P1");
        p.setName("Test");
        when(passengerService.addPassenger(any(Passenger.class))).thenReturn(Mono.just(p));
        webClient.post().uri("/api/flight/passenger/add").bodyValue(p).exchange()
                .expectStatus().isCreated().expectBody(Passenger.class);
    }

    @Test
    void testGetPassenger() {
        Passenger p = new Passenger();
        p.setId("P1");
        when(passengerService.getPassengerById("P1")).thenReturn(Mono.just(p));
        webClient.get().uri("/api/flight/passenger/P1").exchange()
                .expectStatus().isOk().expectBody(Passenger.class);
    }

    @Test
    void testGetPassengersByBooking() {
        when(passengerService.getPassengersByBookingId("B1")).thenReturn(Flux.empty());
        webClient.get().uri("/api/flight/passenger/booking/B1").exchange()
                .expectStatus().isOk().expectBodyList(Passenger.class).hasSize(0);
    }

    @Test
    void testGetAllPassengers() {
        when(passengerService.getAllPassengers()).thenReturn(Flux.empty());
        webClient.get().uri("/api/flight/passenger/all").exchange().expectStatus().isOk();
    }

    @Test
    void testDeletePassenger() {
        when(passengerService.deletePassenger("P1")).thenReturn(Mono.empty());
        webClient.delete().uri("/api/flight/passenger/delete/P1").exchange().expectStatus().isOk();
    }
}
