# Flight Booking System – Reactive Spring WebFlux + MongoDB

A fully reactive, high-performance **Flight Booking System** built using **Spring Boot WebFlux**, **Reactive MongoDB**, and **Project Reactor**.

This project demonstrates a complete asynchronous architecture for managing airlines, flights, bookings, passengers, and search functionality.

---

## **1. Project Overview**

The Flight Booking System allows:

- Airline registration  
- Flight inventory management  
- Searching flights  
- Booking tickets  
- Cancelling tickets  
- Viewing booking history  
- Passenger management  

Every operation uses **Reactive Streams (Flux/Mono)**.

---

## **2. Tech Stack**

### **Backend**
- Java 17  
- Spring Boot 3.3.5  
- Spring WebFlux  
- Spring Reactive MongoDB  
- Lombok  
- Maven  

### **Reactive Framework**
- Project Reactor  
  - `Mono<T>` for single async values  
  - `Flux<T>` for streaming sequences  

### **Database**
- MongoDB (Reactive Driver)

### **Testing**
- JUnit 5  
- Mockito  
- WebTestClient  
- Reactor Test (`StepVerifier`)  
- Jacoco (Coverage)

### **Performance & Quality**
- Apache JMeter  
- SonarQube Cloud
- Jacoco Coverage Report (89%)

---

## **3. Core Features**

- Reactive, non-blocking API design  
- Separate layered architecture  
- DTO layer for request abstraction  
- Global exception handler  
- Clean repository interfaces  
- High concurrency support  
- DTO → Entity mapping  
- JMeter performance validation  
- SonarQube code quality optimization  

---
---

## 4. Architecture Diagram

![Architecture Diagram](https://raw.githubusercontent.com/yme754/flight-booking-system-webflux-mongodb/main/flight%20booking%20system%20architecture%20diagram%20.png)
