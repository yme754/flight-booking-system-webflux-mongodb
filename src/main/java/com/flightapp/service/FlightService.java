package com.flightapp.service;

import org.springframework.http.ResponseEntity;

import com.flightapp.entity.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {
	Mono<Flight> addFlightInventory(Flight flight);
    Flux<Flight> searchFlights(String fromPlace, String toPlace);
    Flux<Flight> getAllFlights();
    Mono<Flight> getFlight(String id);
	Mono<ResponseEntity<Flight>> getFlightById(String flightId);
}
