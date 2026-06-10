package com.medicalapp.sensor;

import com.medicalapp.model.Patient;
import com.medicalapp.model.Vitals;
import com.medicalapp.repository.HospitalRepository;
import com.medicalapp.service.DosageService;
import com.medicalapp.service.MedicalPrescription;
import java.util.Random;

public class PatientMonitorSensor implements Runnable {
    private final Patient patient;
    private final HospitalRepository repository;
    private final Random random = new Random();

    public PatientMonitorSensor(Patient patient, HospitalRepository repository) {
        this.patient = patient;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            // Generare date senzori
            int pulse = 65 + random.nextInt(55); // Ritm cardiac variabil (65-120)
            int systolic = 115 + random.nextInt(45); // Tensiune variabilă (115-160)

            Vitals reading = new Vitals(pulse, systolic);
            patient.addVitals(reading);

            // Evaluăm automat starea folosind Lambda compus din DosageService
            MedicalPrescription prescription = DosageService.masterHospitalRule.analyze(reading);

            if (prescription.isUrgent()) {
                String alert = String.format("ALERTĂ PACIENT %s (%s) -> %s [Valori: %s]",
                        patient.getName(), patient.getId(), prescription, reading);
                repository.triggerEmergency(alert);
            }
        } catch (Exception e) {
            System.err.println("Eroare la citirea senzorului: " + e.getMessage());
        }
    }
}