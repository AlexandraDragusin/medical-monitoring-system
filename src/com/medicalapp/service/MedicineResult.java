package com.medicalapp.service;

public class MedicineResult {
    private final String name;
    private final int dose;
    private final boolean urgent;

    public MedicineResult(String name, int dose, boolean urgent) {
        this.name = name;
        this.dose = dose;
        this.urgent = urgent;
    }

    public String getName() {
        return name;
    }

    public int getDose() {
        return dose;
    }

    public boolean isUrgent() {
        return urgent;
    }

    @Override
    public String toString() {
        return (urgent ? "[URGENT] " : "[OK] ") + name + " (" + dose + "mg)";
    }
}