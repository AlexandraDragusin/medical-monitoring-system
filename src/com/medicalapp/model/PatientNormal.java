package com.medicalapp.model;

public class PatientNormal extends Patient {
    private final String roomNumber;

    public PatientNormal(String id, String name, int age, String roomNumber) {
        super(id, name, age);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }

    @Override
    public String getType() {
        return "NORMAL - Room " + roomNumber;
    }
}