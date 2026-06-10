package com.medicalapp.model;

// Pacient la terapie intensiva
public class PatientCritical extends Patient {
    private final int bedNumber;

    public PatientCritical(String id, String name, int age, int bedNumber) {
        super(id, name, age);
        this.bedNumber = bedNumber;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    @Override
    public boolean isConditionDangerous(MedicalData data) {
        if (getAge() >= 60) {
            return data.getPulse() > 95 || data.getPressure() > 135;
        }

        return data.getPulse() > 105 || data.getPressure() > 145;
    }
}