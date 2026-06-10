package com.medicalapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Patient {
    private final String id;
    private final String name;
    private final int age;
    // Toti senzorii vor scrie date in aceasta lista, avem nevoie de sincronizare pentru a preveni problemele de concurenta
    private final List<Vitals> vitalsHistory = Collections.synchronizedList(new ArrayList<>());
    private String currentTreatment;

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public abstract String getPatientType();

    public void addVitals(Vitals vitals) {
        this.vitalsHistory.add(vitals);
    }

    // Returnăm o listă unmodifiable pentru a respecta încapsularea
    public List<Vitals> getVitalsHistory() {
        return Collections.unmodifiableList(vitalsHistory);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCurrentTreatment() { return currentTreatment; }
    public void setCurrentTreatment(String currentTreatment) { this.currentTreatment = currentTreatment; }
}