package com.flightapp.service;

import com.flightapp.entity.Flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightService {
	Mono<Flight> addFlightInventory(Flight flight);
    Flux<Flight> searchFlights(String fromPlace, String toPlace);
    Mono<Flight> getFlight(Integer id);
}
