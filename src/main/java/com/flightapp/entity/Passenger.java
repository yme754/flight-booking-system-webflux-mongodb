package com.flightapp.entity;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
	@NotBlank(message = "Name cannot be blank")
	private String name;
	@NotBlank(message = "Gender is required")
    private String gender;
	@Min(value = 1, message = "Age must be greater than 0")
    private int age;
    private String email;
}
