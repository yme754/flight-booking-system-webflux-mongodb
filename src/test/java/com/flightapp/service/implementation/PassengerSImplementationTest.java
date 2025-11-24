package com.flightapp.service.implementation;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightapp.entity.Passenger;
import com.flightapp.repository.PassengerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class PassengerSImplementationTest {
	@Mock
    private PassengerRepository passengerRepo;
    private PassengerSImplementation service;

    @BeforeEach
    void init() {
        service = new PassengerSImplementation(passengerRepo);
    }

    @Test
    void testAddPassenger() {
        Passenger p = new Passenger();
        p.setId("P1");
        p.setName("John");
        when(passengerRepo.save(p)).thenReturn(Mono.just(p));
        StepVerifier.create(service.addPassenger(p)).expectNext(p).verifyComplete();
    }

    @Test
    void testGetPassengerById() {
        Passenger p = new Passenger();
        p.setId("P2");
        when(passengerRepo.findById("P2")).thenReturn(Mono.just(p));
        StepVerifier.create(service.getPassengerById("P2")).expectNext(p).verifyComplete();
    }

    @Test
    void testGetPassengersByBookingId() {
        when(passengerRepo.findByBookingId("B1")).thenReturn(Flux.empty());
        StepVerifier.create(service.getPassengersByBookingId("B1")).verifyComplete();
    }
}
