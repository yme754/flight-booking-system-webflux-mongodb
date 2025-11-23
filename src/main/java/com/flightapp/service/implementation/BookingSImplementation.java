package com.flightapp.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.flightapp.entity.Booking;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.service.BookingService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookingSImplementation implements BookingService{
	private final FlightRepository flightRepo;
	private final BookingRepository bookingRepo;
	public BookingSImplementation(FlightRepository flightRepo, BookingRepository bookingRepo) {
		this.flightRepo = flightRepo;
		this.bookingRepo = bookingRepo;
	}
	@Override
    public Mono<Booking> bookTicket(String flightId, Booking bookingRequest) {

        return flightRepo.findById(flightId)
            .flatMap(flight -> {
                if (bookingRequest.getSeatCount() != bookingRequest.getSeatNumbers().size()) {
                    return Mono.error(new RuntimeException("Seat count mismatch"));
                }
                if (flight.getAvailableSeats() < bookingRequest.getSeatCount()) {
                    return Mono.error(new RuntimeException("Not enough seats available"));
                }
                List<String> seats = flight.getSeats();
                for (String seatNo : bookingRequest.getSeatNumbers()) {
                    if (!seats.contains(seatNo)) 
                    	return Mono.error(new RuntimeException("Seat " + seatNo + " not available"));
                }
                flight.setAvailableSeats(flight.getAvailableSeats()-bookingRequest.getSeatCount());
                return flightRepo.save(flight)
                    .flatMap(updatedFlight -> {
                        bookingRequest.setFlightId(flightId);
                        bookingRequest.setAirlineId(updatedFlight.getAirlineId());
                        bookingRequest.setCreatedAt(LocalDateTime.now());
                        bookingRequest.setPnr(generatePNR());
                        return bookingRepo.save(bookingRequest);
                    });
            });
    }
    private String generatePNR() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    @Override
    public Mono<Booking> getTicketByPnr(String pnr) {
        return bookingRepo.findByPnr(pnr);
    }

    @Override
    public Flux<Booking> getHistoryByEmail(String email) {
        return bookingRepo.findByEmail(email);
    }

    @Override
    public Mono<Void> cancelTicket(String pnr) {
        return bookingRepo.findByPnr(pnr)
            .switchIfEmpty(Mono.error(new RuntimeException("Invalid PNR")))
            .flatMap(bookingRepo::delete);
    }
}
