package com.flightapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.flightapp.dto.BookingRequest;
import com.flightapp.entity.Booking;
import com.flightapp.service.BookingService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(BookingController.class)
public class BookingControllerTest {
	@Autowired
    private WebTestClient webClient;
    @MockBean
    private BookingService bookingService;

    @Test
    void testBookTicket() {
        BookingRequest req = new BookingRequest();
        req.setEmail("test@gmail.com");
        req.setSeatCount(1);
        req.setSeatNumbers(List.of("S1"));

        Booking saved = new Booking();
        saved.setPnr("PNR123");

        when(bookingService.bookTicket(eq("F1"), any(Booking.class)))
                .thenReturn(Mono.just(saved));
        webClient.post().uri("/api/flight/booking/F1").bodyValue(req).exchange()
                .expectStatus().isCreated()
                .expectBody(Booking.class)
                .value(b -> b.getPnr().equals("PNR123"));
    }

    @Test
    void testGetTicket() {
        Booking b = new Booking();
        b.setPnr("PXYZ");
        when(bookingService.getTicketByPnr("PXYZ"))
                .thenReturn(Mono.just(b));
        webClient.get()
                .uri("/api/flight/ticket/PXYZ").exchange().expectStatus().isOk().expectBody(Booking.class);
    }

    @Test
    void testGetHistory() {
        when(bookingService.getHistoryByEmail("abc@gmail.com"))
                .thenReturn(Flux.empty());
        webClient.get()
                .uri("/api/flight/booking/history/abc@gmail.com").exchange().expectStatus().isOk()
                .expectBodyList(Booking.class).hasSize(0);
    }

    @Test
    void testCancelTicket() {
        when(bookingService.cancelTicket("PNR12"))
                .thenReturn(Mono.empty());
        webClient.delete().uri("/api/flight/booking/cancel/PNR12").exchange().expectStatus().isOk();
    }
}
