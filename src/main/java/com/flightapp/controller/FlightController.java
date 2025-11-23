package com.flightapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.FlightSearchRequest;
import com.flightapp.entity.Flight;
import com.flightapp.service.FlightService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1.0/flight")
public class FlightController {
	@Autowired
    private FlightService flightService;
    @PostMapping("/search")
    public Flux<Flight> searchFlights(@RequestBody FlightSearchRequest req) {
        return flightService.searchFlights(req.getFromPlace(),req.getToPlace());
    }
}
