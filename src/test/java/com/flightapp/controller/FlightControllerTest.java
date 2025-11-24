package com.flightapp.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.flightapp.dto.FlightSearchRequest;
import com.flightapp.entity.Flight;
import com.flightapp.service.FlightService;

import reactor.core.publisher.Flux;

@WebFluxTest(FlightController.class)
class FlightControllerTest {
	@Autowired
    private WebTestClient webClient;
    @MockBean
    private FlightService flightService;

    @Test
    void testSearchFlights() {
        Flight f = new Flight();
        f.setId("F1");
        when(flightService.searchFlights("HYD","BLR")).thenReturn(Flux.just(f));

        FlightSearchRequest req = new FlightSearchRequest();
        req.setFromPlace("HYD");
        req.setToPlace("BLR");
        webClient.post().uri("/api/flight/search").bodyValue(req).exchange().expectStatus().isOk()
                .expectBodyList(Flight.class).hasSize(1);
    }


    @Test
    void testGetAllFlights() {
        when(flightService.getAllFlights()).thenReturn(Flux.empty());
        webClient.get().uri("/api/flight/get/flights").exchange()
                .expectStatus().isOk().expectBodyList(Flight.class).hasSize(0);
    }
}
