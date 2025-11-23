package com.flightapp.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flightapp.entity.Flight;
import com.flightapp.entity.Seat;
import com.flightapp.repository.FlightRepository;
import com.flightapp.service.FlightService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightSImplementation implements FlightService{
	private final FlightRepository flightRepo;
	public FlightSImplementation(FlightRepository flightRepo) {
		this.flightRepo = flightRepo;
	}
	@Override
	public Mono<Flight> addFlightInventory(Flight flight) {
		List<Seat> seats = new ArrayList<>();
        int seatCnt = flight.getAvailableSeats();
        for (int i = 1; i <= seatCnt; i++) {
            seats.add(new Seat("S" + i, true));
        }
        flight.setSeats(seats);
        return flightRepo.save(flight);
	}
	@Override
	public Flux<Flight> searchFlights(String fromPlace, String toPlace) {
		return flightRepo.findByFromPlaceAndToPlace(fromPlace, toPlace);
	}
	@Override
	public Mono<Flight> getFlight(String id) {
		return flightRepo.findById(id);
	}
	@Override
	public Flux<Flight> getAllFlights() {
		return flightRepo.findAll();
	}
}
