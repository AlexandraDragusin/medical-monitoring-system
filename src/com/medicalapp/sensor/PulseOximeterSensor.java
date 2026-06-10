package com.medicalapp.sensor;

import com.medicalapp.model.Patient;
import com.medicalapp.model.Vitals;
import com.medicalapp.repository.HospitalRepository;
import java.util.Random;

public class PulseOximeterSensor implements MedicalSensor {
    private final Patient patient;
    private final HospitalRepository repository;
    private final Random random = new Random();

    public PulseOximeterSensor(Patient patient, HospitalRepository repository) {
        this.patient = patient;
        this.repository = repository;
    }

    @Override
    public Vitals readTelemetry() {
        int pulse = 70 + random.nextInt(45);
        int systolic = 120 + random.nextInt(20);
        return new Vitals(pulse, systolic);
    }

    @Override
    public String getSensorDeviceName() {
        return "PulseOximeter-v4";
    }

    @Override
    public void run() {
        try {
            Vitals reading = readTelemetry();
            patient.addVitals(reading);
        } catch (Exception e) {
            System.err.println("Eroare dispozitiv " + getSensorDeviceName());
        }
    }
}