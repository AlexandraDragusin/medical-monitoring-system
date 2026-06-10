package com.medicalapp.sensor;

import com.medicalapp.model.Patient;
import com.medicalapp.model.MedicalData;
import com.medicalapp.repository.HospitalData;
import java.util.Random;

public class DevicePulse implements Runnable {
    private final Patient patient;
    private final Random random = new Random();

    public DevicePulse(Patient patient, HospitalData hospitalData) {
        this.patient = patient;
    }

    @Override
    public void run() {
        try {
            int pulse = 70 + random.nextInt(45);
            int pressure = 120 + random.nextInt(20);
            patient.addData(new MedicalData(pulse, pressure));
        } catch (Exception e) {
            System.err.println("Device error");
        }
    }
}