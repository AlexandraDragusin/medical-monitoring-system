package com.medicalapp.model;

import com.medicalapp.exception.BadDataException;
import java.time.LocalDateTime;

public class MedicalData {
    private final int pulse;
    private final int pressure;
    private final LocalDateTime time;

    public MedicalData(int pulse, int pressure) {
        if (pulse < 0 || pressure < 0) {
            throw new IllegalArgumentException("Values cannot be negative!");
        }

        if (pulse > 220 || pressure > 240) {
            throw new BadDataException(pulse, pressure, "Danger! Critical values!");
        }

        this.pulse = pulse;
        this.pressure = pressure;

        this.time = LocalDateTime.now();
    }

    public int getPulse() {
        return pulse;
    }

    public int getPressure() {
        return pressure;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Pulse: " + pulse + " bpm, Pressure: " + pressure + " mmHg at " + time;
    }
}