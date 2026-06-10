package com.medicalapp.model;

public class PatientCritical extends Patient {
    private final int bedNumber;

    public PatientCritical(String id, String name, int age, int bedNumber) {
        super(id, name, age);
        this.bedNumber = bedNumber;
    }

    public int getBedNumber() { return bedNumber; }

    @Override
    public String getType() {
        return "CRITICAL - ICU Bed #" + bedNumber;
    }
}