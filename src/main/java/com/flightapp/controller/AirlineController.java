package com.flightapp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.AirlineRequest;
import com.flightapp.dto.FlightInventoryRequest;
import com.flightapp.entity.Airline;
import com.flightapp.entity.Flight;
import com.flightapp.service.AirlineService;
import com.flightapp.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight/airline")
public class AirlineController {
	private AirlineService airlineService;
	private FlightService flightService;
	public AirlineController(AirlineService airlineService, FlightService flightService) {
        this.airlineService = airlineService;
        this.flightService = flightService;
    }
	
	@GetMapping("/inventory")
	public Flux<Map<String, Object>> getAllInventory() {
	    return flightService.getAllFlights()
	        .map(f -> Map.of(
	            "id", f.getId(),
	            "airlineId", f.getAirlineId(),
	            "fromPlace", f.getFromPlace(),
	            "toPlace", f.getToPlace(),
	            "availableSeats", f.getAvailableSeats(), "seats", f.getSeats()
	        ));
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Airline> addAirline(@RequestBody AirlineRequest req) {
	    Airline airline = new Airline();
	    airline.setName(req.getName());
	    airline.setLogoUrl(req.getLogoUrl());
	    return airlineService.addAirline(airline);
	}
	
	@PostMapping("/inventory/add")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Map<String, Object>> addInventory(@RequestBody FlightInventoryRequest req) {
	    Flight flight = new Flight();
	    flight.setAirlineId(req.getAirlineId());
	    flight.setFromPlace(req.getFromPlace());
	    flight.setToPlace(req.getToPlace());
	    flight.setAvailableSeats(req.getAvailableSeats());
	    flight.setOneWayPrice(req.getOneWayPrice());
	    flight.setRoundTripPrice(req.getRoundTripPrice());
	    return flightService.addFlightInventory(flight)
	           .map(saved -> Map.of("id", saved.getId(), "airlineId", saved.getAirlineId()));
	}


}
