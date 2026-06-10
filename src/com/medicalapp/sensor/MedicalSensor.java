package com.medicalapp.sensor;

import com.medicalapp.model.MedicalData;

public interface MedicalSensor extends Runnable {
    MedicalData readTelemetry();
    String getSensorDeviceName();
}