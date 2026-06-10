package com.medicalapp.exception;

public class InvalidTelemetryDataException extends Exception {
    public InvalidTelemetryDataException(String message) {
        super(message);
    }
}