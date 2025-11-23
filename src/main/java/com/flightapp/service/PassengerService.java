package com.flightapp.service;

import com.flightapp.entity.Passenger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PassengerService {
	Mono<Passenger> addPassenger(Passenger passenger);
    Mono<Passenger> getPassengerById(String id);
    Flux<Passenger> getPassengersByBookingId(String bookingId);
    Mono<Void> deletePassenger(String id);
    Flux<Passenger> getAllPassengers();
}
