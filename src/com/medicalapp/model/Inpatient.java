package com.medicalapp.model;

public class Inpatient extends Patient {
    private final String roomNumber;

    public Inpatient(String id, String name, int age, String roomNumber) {
        super(id, name, age);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }

    @Override
    public String getPatientType() {
        return "STATIONAR (Saloane Normale) - Camera " + roomNumber;
    }
}