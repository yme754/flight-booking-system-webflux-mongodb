package com.flight.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
	private String name;
    private String gender;
    private int age;
    private String email;
}
