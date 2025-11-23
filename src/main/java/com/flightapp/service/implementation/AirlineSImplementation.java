package com.flightapp.service.implementation;

import org.springframework.stereotype.Service;

import com.flightapp.entity.Airline;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.service.AirlineService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AirlineSImplementation implements AirlineService{
	private final AirlineRepository airlineRepo;
	public AirlineSImplementation(AirlineRepository airlineRepo) {
		this.airlineRepo = airlineRepo;
	}
	@Override
	public Mono<Airline> addAirline(Airline airline) {
		return airlineRepo.save(airline);
	}
	@Override
	public Flux<Airline> getAllAirlines() {
		return airlineRepo.findAll();
	}
	@Override
	public Mono<Airline> getAirlineById(Integer id) {
		return airlineRepo.findById(id);
	}

}
