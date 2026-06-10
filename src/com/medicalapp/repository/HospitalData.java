package com.medicalapp.repository;

import com.medicalapp.model.Patient;
import com.medicalapp.exception.PatientErrorException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HospitalData {
    // Map pentru stocarea pacientilor, care poate fi accesata concurent de mai multe fire de executie
    private final Map<String, Patient> map = new ConcurrentHashMap<>();

    // Coada pentru stocarea mesajelor de alerta
    private final Queue alerts = new Queue();

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
        alerts.put(message);
    }

    public Queue getAlerts() {
        return alerts;
    }
}