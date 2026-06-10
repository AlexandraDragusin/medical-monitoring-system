package com.medicalapp.sensor;

import com.medicalapp.model.Vitals;

public interface MedicalSensor extends Runnable {
    Vitals readTelemetry();
    String getSensorDeviceName();
}