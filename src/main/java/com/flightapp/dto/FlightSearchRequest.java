package com.flightapp.dto;

import java.time.LocalDateTime;

import com.flightapp.entity.enums.TripType;

import lombok.Data;

@Data
public class FlightSearchRequest {
	private String fromPlace;
	private String toPlace;
    private LocalDateTime travelDate;
    private TripType tripType;
}
