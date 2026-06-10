package com.medicalapp.model;

public class IcuPatient extends Patient {
    private final int ventilatorId;

    public IcuPatient(String id, String name, int age, int ventilatorId) {
        super(id, name, age);
        this.ventilatorId = ventilatorId;
    }

    public int getVentilatorId() { return ventilatorId; }

    @Override
    public String getPatientType() {
        return "CRITIC (Terapie Intensivă) - Ventilator #" + ventilatorId;
    }
}