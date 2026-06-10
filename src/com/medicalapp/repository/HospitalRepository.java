package com.medicalapp.repository;

import com.medicalapp.model.Patient;
import com.medicalapp.model.Vitals;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class HospitalRepository {
    // Un map pentru stocarea pacienților, accesat de mai multe fire de execuție
    private final Map<String, Patient> patientsMap = new ConcurrentHashMap<>();

    // O coadă pentru alerte medicale
    private final LinkedBlockingQueue<String> emergencyAlertsQueue = new LinkedBlockingQueue<>();

    public void registerPatient(Patient patient) {
        patientsMap.put(patient.getId(), patient);
    }

    public Patient findPatientById(String id) {
        Patient patient = patientsMap.get(id);

        if (patient == null) {
            throw new com.medicalapp.exception.PatientNotFoundException("Pacientul cu ID-ul " + id + " nu este înregistrat in baza de date a spitalului!");
        }

        return patient;
    }

    public Collection<Patient> getAllPatients() {
        return Collections.unmodifiableCollection(patientsMap.values());
    }

    public void triggerEmergency(String alertMessage) {
        emergencyAlertsQueue.offer(alertMessage);
    }

    public LinkedBlockingQueue<String> getEmergencyAlertsQueue() {
        return emergencyAlertsQueue;
    }
}