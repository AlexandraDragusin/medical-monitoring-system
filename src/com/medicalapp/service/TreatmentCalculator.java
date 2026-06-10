package com.medicalapp.service;

import com.medicalapp.model.Vitals;

@FunctionalInterface
public interface TreatmentCalculator {
    MedicalPrescription analyze(Vitals vitals);

    default TreatmentCalculator andThenCheck(TreatmentCalculator nextCheck) {
        return vitals -> {
            MedicalPrescription primary = this.analyze(vitals);

            if (primary.isUrgent()) return primary;

            return nextCheck.analyze(vitals);
        };
    }
}