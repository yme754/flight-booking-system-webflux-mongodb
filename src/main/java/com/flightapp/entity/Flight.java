package com.flightapp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
	@Id
    private Integer id;
    private Integer airlineId;
    private String fromPlace;
    private String toPlace;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int availableSeats;
    private long oneWayPrice;
    private long roundTripPrice;
    private List<Seat> seats = new ArrayList<>();
}
