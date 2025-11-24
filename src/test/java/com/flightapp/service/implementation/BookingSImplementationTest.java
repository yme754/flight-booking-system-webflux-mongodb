package com.flightapp.service.implementation;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightapp.entity.Booking;
import com.flightapp.entity.Flight;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class BookingSImplementationTest {
	@Mock
    private FlightRepository flightRepo;
    @Mock
    private BookingRepository bookingRepo;
    private BookingSImplementation service;

    @BeforeEach
    void init() {
        service = new BookingSImplementation(flightRepo, bookingRepo);
    }

    @Test
    void testBookTicketSuccess() {
        Flight flight = new Flight();
        flight.setId("F1");
        flight.setAirlineId("A1");
        flight.setAvailableSeats(3);
        flight.setSeats(List.of("S1","S2","S3"));
        Booking req = new Booking();
        req.setEmail("abc@gmail.com");
        req.setSeatCount(1);
        req.setSeatNumbers(List.of("S1"));
        when(flightRepo.findById("F1")).thenReturn(Mono.just(flight));
        when(flightRepo.save(flight)).thenReturn(Mono.just(flight));
        when(bookingRepo.save(req)).thenReturn(Mono.just(req));
        StepVerifier.create(service.bookTicket("F1", req)).expectNext(req).verifyComplete();
    }

    @Test
    void testBookTicket_NotEnoughSeats() {
        Flight flight = new Flight();
        flight.setAvailableSeats(1);
        flight.setSeats(List.of("S1"));
        Booking req = new Booking();
        req.setSeatCount(2);
        req.setSeatNumbers(List.of("S1","S2"));
        when(flightRepo.findById("F1")).thenReturn(Mono.just(flight));
        StepVerifier.create(service.bookTicket("F1", req)).expectErrorMatches(ex -> ex.getMessage()
        		.contains("Not enough seats")).verify();
    }

    @Test
    void testBookTicket_SeatNotAvailable() {
        Flight flight = new Flight();
        flight.setAvailableSeats(2);
        flight.setSeats(List.of("S1","S2"));
        Booking req = new Booking();
        req.setSeatCount(1);
        req.setSeatNumbers(List.of("S5"));
        when(flightRepo.findById("F1")).thenReturn(Mono.just(flight));
        StepVerifier.create(service.bookTicket("F1", req)).expectErrorMatches(ex -> ex.getMessage()
        		.contains("not available")).verify();
    }

    @Test
    void testGetTicketByPnr() {
        Booking b = new Booking();
        b.setPnr("XYZ");
        when(bookingRepo.findByPnr("XYZ")).thenReturn(Mono.just(b));
        StepVerifier.create(service.getTicketByPnr("XYZ")).expectNext(b).verifyComplete();
    }

    @Test
    void testGetHistoryByEmail() {
        when(bookingRepo.findByEmail("abc@gmail.com")).thenReturn(Flux.empty());
        StepVerifier.create(service.getHistoryByEmail("abc@gmail.com")).verifyComplete();
    }


    @Test
    void testCancelTicket_InvalidPNR() {
        when(bookingRepo.findByPnr("BAD")).thenReturn(Mono.empty());
        StepVerifier.create(service.cancelTicket("BAD")).expectErrorMatches(e -> e.getMessage()
        		.contains("Invalid PNR")).verify();
    }
}
