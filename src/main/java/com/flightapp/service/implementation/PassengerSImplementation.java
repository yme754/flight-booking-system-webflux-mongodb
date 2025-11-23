package com.flightapp.service.implementation;

import com.flightapp.entity.Passenger;
import com.flightapp.repository.PassengerRepository;
import com.flightapp.service.PassengerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PassengerSImplementation implements PassengerService{
	private final PassengerRepository passengerRepo;
    public PassengerSImplementation(PassengerRepository passengerRepo) {
        this.passengerRepo = passengerRepo;
    }
    @Override
    public Mono<Passenger> addPassenger(Passenger passenger) {
        return passengerRepo.save(passenger);
    }
    @Override
    public Mono<Passenger> getPassengerById(String id) {
        return passengerRepo.findById(id);
    }
    @Override
    public Flux<Passenger> getPassengersByBookingId(String bookingId) {
        return passengerRepo.findByBookingId(bookingId);
    }
    @Override
    public Mono<Void> deletePassenger(String id) {
        return passengerRepo.deleteById(id);
    }
    @Override
    public Flux<Passenger> getAllPassengers() {
        return passengerRepo.findAll();
    }
}
