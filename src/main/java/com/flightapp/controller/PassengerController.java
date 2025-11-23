package com.flightapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.entity.Passenger;
import com.flightapp.service.PassengerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight/passenger")
public class PassengerController {
	private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Passenger> addPassenger(@RequestBody Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }

    @GetMapping("/{id}")
    public Mono<Passenger> getPassenger(@PathVariable String id) {
        return passengerService.getPassengerById(id);
    }

    @GetMapping("/booking/{bookingId}")
    public Flux<Passenger> getByBooking(@PathVariable String bookingId) {
        return passengerService.getPassengersByBookingId(bookingId);
    }

    @GetMapping("/all")
    public Flux<Passenger> getAll() {
        return passengerService.getAllPassengers();
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deletePassenger(@PathVariable String id) {
        return passengerService.deletePassenger(id);
    }
}
