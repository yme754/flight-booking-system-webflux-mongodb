package com.flightapp.dto;

import lombok.Data;

@Data
public class FlightInventoryRequest {
	private String airlineId;
    private String fromPlace;
    private String toPlace;
    private int availableSeats;
    private long oneWayPrice;
    private long roundTripPrice;
}
