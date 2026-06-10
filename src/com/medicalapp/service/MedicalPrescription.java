package com.medicalapp.service;

public class MedicalPrescription {
    private final String medication;
    private final int dosageMg;
    private final boolean isUrgent;

    public MedicalPrescription(String medication, int dosageMg, boolean isUrgent) {
        this.medication = medication;
        this.dosageMg = dosageMg;
        this.isUrgent = isUrgent;
    }

    public String getMedication() { return medication; }
    public int getDosageMg() { return dosageMg; }
    public boolean isUrgent() { return isUrgent; }

    @Override
    public String toString() {
        return (isUrgent ? "[CRITIC] " : "[STABIL] ") + medication + " - " + dosageMg + "mg";
    }
}