package com.medicalapp.exception;

public class CriticalVitalsException extends RuntimeException {
    public CriticalVitalsException(String message) {
        super(message);
    }
}