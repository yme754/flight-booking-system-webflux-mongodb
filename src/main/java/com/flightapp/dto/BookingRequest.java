package com.flightapp.dto;

import java.util.List;

import com.flightapp.entity.Passenger;
import com.flightapp.entity.enums.MealPreference;
import com.flightapp.entity.enums.TripType;

import lombok.Data;

@Data
public class BookingRequest {
	private String email;
    private int seatCount;
    private List<String> seatNumbers;
    private List<Passenger> passengers;
    private MealPreference mealPreference;
    private TripType tripType;
}
