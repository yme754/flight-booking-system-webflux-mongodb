package com.flightapp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flightapp.entity.enums.BookingStatus;
import com.flightapp.entity.enums.MealPreference;
import com.flightapp.entity.enums.TripType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	@Id
    private Integer id;
    private String pnr;
    private Integer flightId;
    private Integer airlineId;
    @NotBlank
    private String email;
    private int seatCount;
    private TripType tripType;
    private MealPreference mealPreference;
    private List<Passenger> passengers = new ArrayList<>();
    private List<String> seatNumbers = new ArrayList<>();
    private long totalAmount;
    private LocalDateTime createdAt;
    private BookingStatus status = BookingStatus.ACTIVE;
}
