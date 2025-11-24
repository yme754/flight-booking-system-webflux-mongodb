package com.flightapp.service.implementation;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import com.flightapp.entity.Airline;
import com.flightapp.repository.AirlineRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class AirlineSImplementationTest {
	private final AirlineRepository repo = Mockito.mock(AirlineRepository.class);
    private final AirlineSImplementation service = new AirlineSImplementation(repo);

    @Test
    void testGetAllAirlines() {
        Airline a = new Airline("A1", "Indigo", "logo.png");
        when(repo.findAll()).thenReturn(Flux.just(a));
        StepVerifier.create(service.getAllAirlines()).expectNext(a).verifyComplete();
    }

    @Test
    void testGetAirlineById() {
        Airline a = new Airline("A1", "Indigo", "logo.png");
        when(repo.findById("A1")).thenReturn(Mono.just(a));
        StepVerifier.create(service.getAirlineById("A1")).expectNext(a).verifyComplete();
    }
}
