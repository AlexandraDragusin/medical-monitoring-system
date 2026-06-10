package com.medicalapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Patient {
    private final String id;
    private final String name;
    private final int age;
    private final List<MedicalData> history = Collections.synchronizedList(new ArrayList<>());

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public abstract String getType();

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