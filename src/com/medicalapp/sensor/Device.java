package com.medicalapp.sensor;

import com.medicalapp.model.MedicalData;

// O interfață simplă pentru toate dispozitivele hardware din spital
public interface Device extends Runnable {
    MedicalData readData();
    String getName();
}