package com.medicalapp.repository;

import com.medicalapp.model.Patient;
import com.medicalapp.exception.PatientErrorException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class HospitalData {
    private final Map<String, Patient> map = new ConcurrentHashMap<>();
    private final LinkedBlockingQueue<String> alerts = new LinkedBlockingQueue<>();

    public void addPatient(Patient p) {
        map.put(p.getId(), p);
    }

    public Patient findPatient(String id) {
        Patient p = map.get(id);
        if (p == null) {
            throw new PatientErrorException(id, "Patient with ID " + id + " not found!");
        }
        return p;
    }

    public Collection<Patient> getAll() {
        return Collections.unmodifiableCollection(map.values());
    }

    public void addAlert(String message) {
        alerts.offer(message);
    }

    public LinkedBlockingQueue<String> getAlerts() {
        return alerts;
    }
}