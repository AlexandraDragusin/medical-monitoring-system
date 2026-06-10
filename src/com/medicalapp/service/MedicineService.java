package com.medicalapp.service;

import com.medicalapp.model.MedicalData;

public class MedicineService {

    public static final PatientCheck pulseCheck = data -> {
        if (data.getPulse() > 110) {
            return new MedicineResult("Beta-Blocker", 10, true);
        }
        return new MedicineResult("None", 0, false);
    };

    public static final PatientCheck pressureCheck = data -> {
        if (data.getPressure() > 150) {
            return new MedicineResult("ACE-Inhibitor", 20, true);
        }
        return new MedicineResult("None", 0, false);
    };

    public static final PatientCheck masterCheck = pulseCheck.andThenCheck(pressureCheck);

    public static boolean isSystemBusy(int activeSensors) {
        return activeSensors > 100;
    }
}