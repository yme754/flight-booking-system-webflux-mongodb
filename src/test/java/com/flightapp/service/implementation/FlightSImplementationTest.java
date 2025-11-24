package com.flightapp.service.implementation;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightapp.entity.Flight;
import com.flightapp.repository.FlightRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class FlightSImplementationTest {
	@Mock
    private FlightRepository flightRepo;
    private FlightSImplementation service;

    @BeforeEach
    void init() {
        service = new FlightSImplementation(flightRepo);
    }

    @Test
    void testAddFlightInventory() {
        Flight f = new Flight();
        f.setId("F1");
        f.setAvailableSeats(3);
        when(flightRepo.save(f)).thenReturn(Mono.just(f));
        StepVerifier.create(service.addFlightInventory(f))
                .expectNextMatches(saved -> saved.getSeats().equals(List.of("S1","S2","S3"))).verifyComplete();
    }

    @Test
    void testSearchFlights() {
        Flight f = new Flight();
        f.setId("F1");
        when(flightRepo.findByFromPlaceAndToPlace("HYD", "BLR")).thenReturn(Flux.just(f));
        StepVerifier.create(service.searchFlights("HYD","BLR")).expectNext(f).verifyComplete();
    }

    @Test
    void testGetFlight() {
        Flight f = new Flight();
        f.setId("F10");
        when(flightRepo.findById("F10")).thenReturn(Mono.just(f));
        StepVerifier.create(service.getFlight("F10")).expectNext(f).verifyComplete();
    }
}
