package com.medicalapp.model;

import com.medicalapp.exception.CriticalVitalsException;
import java.time.LocalDateTime;

public class Vitals {
    private final int heartRate;
    private final int bloodPressureSystolic;
    private final LocalDateTime timestamp;

    public Vitals(int heartRate, int bloodPressureSystolic) {
        if (heartRate < 0 || bloodPressureSystolic < 0) {
            throw new IllegalArgumentException("Semnele vitale nu pot avea valori negative!");
        }

        if (heartRate > 220 || bloodPressureSystolic > 240) {
            throw new CriticalVitalsException("Anomalie critică detectată! Valori incompatibile cu profilele standard.");
        }

        this.heartRate = heartRate;
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.timestamp = LocalDateTime.now();
    }

    public int getHeartRate() { return heartRate; }
    public int getBloodPressureSystolic() { return bloodPressureSystolic; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("Puls: %d bpm, Tensiune: %d mmHg la %s",
                heartRate, bloodPressureSystolic, timestamp);
    }
}