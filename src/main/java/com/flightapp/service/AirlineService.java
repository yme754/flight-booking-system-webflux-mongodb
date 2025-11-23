package com.flightapp.service;

import org.springframework.stereotype.Service;

import com.flightapp.entity.Airline;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface AirlineService {
	Mono<Airline> addAirline(Airline airline);
    Flux<Airline> getAllAirlines();
    Mono<Airline> getAirlineById(String id);
}
