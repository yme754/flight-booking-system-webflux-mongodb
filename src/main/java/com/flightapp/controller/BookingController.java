package com.flightapp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.BookingRequest;
import com.flightapp.entity.Booking;
import com.flightapp.service.BookingService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1.0/flight")
public class BookingController {
    private BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    @PostMapping("/booking/{flightId}")
    public Mono<Booking> bookTicket(
    		@PathVariable String flightId,
            @RequestBody BookingRequest request) {
    	Booking booking = new Booking();
        booking.setEmail(request.getEmail());
        booking.setSeatCount(request.getSeatCount());
        booking.setSeatNumbers(request.getSeatNumbers());
        booking.setPassengers(request.getPassengers());
        booking.setMealPreference(request.getMealPreference());
        booking.setTripType(request.getTripType());
        return bookingService.bookTicket(flightId, booking);
    }
    @GetMapping("/ticket/{pnr}")
    public Mono<Booking> getTicket(@PathVariable String pnr) {
        return bookingService.getTicketByPnr(pnr);
    }
    @GetMapping("/booking/history/{email}")
    public Flux<Booking> getHistory(@PathVariable String email) {
        return bookingService.getHistoryByEmail(email);
    }
    @DeleteMapping("/booking/cancel/{pnr}")
    public Mono<Void> cancelTicket(@PathVariable String pnr) {
        return bookingService.cancelTicket(pnr);
    }
}
