package com.flightapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.FlightSearchRequest;
import com.flightapp.entity.Flight;
import com.flightapp.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    private FlightService flightService;
    public FlightController(FlightService flightService) {
    	this.flightService = flightService;
    }
    
    @PostMapping("/search")
    public Flux<Flight> searchFlights(@RequestBody FlightSearchRequest req) {
        return flightService.searchFlights(req.getFromPlace(),req.getToPlace());
    }
    
    @GetMapping("/get/{flightId}")
	public Mono<ResponseEntity<Flight>> getFlight(@PathVariable String flightId) {
		return flightService.getFlightById(flightId);
	}

	@GetMapping("/get/flights")
	public Flux<Flight> getAllFlights() {
		return flightService.getAllFlights();
	}
}
