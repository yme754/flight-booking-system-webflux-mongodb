package com.flightapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.flightapp.entity.Flight;

import reactor.core.publisher.Flux;

public interface FlightRepository extends ReactiveMongoRepository<Flight, Integer>{
	Flux<Flight> findByFromPlaceAndToPlace(String fromPlace, String toPlace);
}
