package com.medicalapp.sensor;

import com.medicalapp.model.Patient;
import com.medicalapp.model.MedicalData;
import com.medicalapp.repository.HospitalData;
import java.util.Random;

public class DevicePulse implements Device {
    private final Patient patient;
    private final Random random = new Random();

    public DevicePulse(Patient patient) {
        this.patient = patient;
    }

    @Override
    public MedicalData readData() {
        int pulse = 70 + random.nextInt(45);
        int pressure = 120 + random.nextInt(20);
        return new MedicalData(pulse, pressure);
    }

    @Override
    public String getName() {
        return "Finger-Oximeter-v4";
    }

    @Override
    public void run() {
        try {
            patient.addData(readData());

        } catch (Exception e) {
            System.err.println("Device error");
        }
    }
}