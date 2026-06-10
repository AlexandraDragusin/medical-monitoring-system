package com.medicalapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Patient {
    private final String id;
    private final String name;
    private final int age;

    // Mai multi senzori vor scrie concurent date in aceasta lista, avem nveoie de sincronizare
    private final List<MedicalData> history = Collections.synchronizedList(new ArrayList<>());

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public abstract boolean isConditionDangerous(MedicalData data);

    public void addData(MedicalData data) {
        this.history.add(data);
    }

    public List<MedicalData> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
}