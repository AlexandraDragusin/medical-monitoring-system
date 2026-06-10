package com.medicalapp.service;

import com.medicalapp.model.MedicalData;

// are o singura metoda abstracta
@FunctionalInterface
public interface PatientCheck {
    MedicineResult check(MedicalData data);

    default PatientCheck andThenCheck(PatientCheck next) {
        return data -> {
            MedicineResult firstResult = this.check(data);

            if (firstResult.isUrgent()) return firstResult;

            return next.check(data);
        };
    }
}