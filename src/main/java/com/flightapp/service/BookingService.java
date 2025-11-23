package com.flightapp.service;

import com.flightapp.entity.Booking;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {
	Mono<Booking> bookTicket(Integer flightId, Booking bookingRequest);
    Mono<Booking> getTicketByPnr(String pnr);
    Flux<Booking> getHistoryByEmail(String email);
    Mono<Void> cancelTicket(String pnr);
}
