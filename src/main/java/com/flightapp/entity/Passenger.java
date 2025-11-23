package com.flightapp.entity;


import org.springframework.data.annotation.Id;

import com.flightapp.entity.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
	@Id
    private String id;
    @NotBlank(message = "Passenger name is required")
    private String name;
    @NotBlank(message = "Gender is required")
    private Gender gender;
    @Positive(message = "Age must be greater than 0")
    @Max(value = 90, message = "Age cannot exceed 90")
    private int age;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    private String bookingId;
}
