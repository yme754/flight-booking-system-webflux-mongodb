package com.flightapp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class GlobalExcpetionHandlerTest {
	private final GlobalExceptionHandler handler = new GlobalExceptionHandler();
	
    @Test
    void testHandleRuntimeException() {
        RuntimeException ex = new RuntimeException("Test Error");
        ResponseEntity<Map<String, String>> response = handler.handleRuntime(ex);
        assertEquals(400, response.getStatusCode().value());
        assertEquals("Test Error", response.getBody().get("error"));
    }

    @Test
    void testHandleGeneralException() {
        Exception ex = new Exception("General Issue");
        ResponseEntity<Map<String, String>> response = handler.handleGeneral(ex);
        assertEquals(500, response.getStatusCode().value());
        assertEquals("Something went wrong", response.getBody().get("error"));
    }
}
