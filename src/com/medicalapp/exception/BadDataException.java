package com.medicalapp.exception;

public class BadDataException extends RuntimeException {
    private final int pulse;
    private final int pressure;

    public BadDataException(int pulse, int pressure, String message) {
        super(message);
        this.pulse = pulse;
        this.pressure = pressure;
    }

    public int getPulse() {
        return pulse;
    }

    public int getPressure() {
        return pressure;
    }
}