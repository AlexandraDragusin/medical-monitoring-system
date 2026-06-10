package com.medicalapp.service;

import com.medicalapp.model.Vitals;

public class DosageService {

    public static final TreatmentCalculator cardiacChecker = vitals -> {
        if (vitals.getHeartRate() > 110) {
            return new MedicalPrescription("Beta-Blocant", 10, true);
        }
        return new MedicalPrescription("Placebo/Monitorizare", 0, false);
    };

    public static final TreatmentCalculator bpChecker = vitals -> {
        if (vitals.getBloodPressureSystolic() > 150) {
            return new MedicalPrescription("Inhibitor ACE", 20, true);
        }
        return new MedicalPrescription("Placebo/Monitorizare", 0, false);
    };

    public static final TreatmentCalculator masterHospitalRule = cardiacChecker.andThenCheck(bpChecker);

    public static boolean isSystemOverloaded(int activeSensors) {
        return activeSensors > 100;
    }
}