package com.medicalapp.tests;

import com.medicalapp.exception.CriticalVitalsException;
import com.medicalapp.exception.PatientNotFoundException;
import com.medicalapp.model.Vitals;
import com.medicalapp.repository.HospitalRepository;
import org.junit.Test;

public class Test2 {

    @Test(expected = PatientNotFoundException.class)
    public void testPatientNotFoundException() {
        HospitalRepository repo = new HospitalRepository();

        // Trebuie să arunce PatientNotFoundException deoarece spitalul e gol
        repo.findPatientById("ID_FANTOMA");
    }

    @Test(expected = CriticalVitalsException.class)
    public void testCriticalVitalsException() {
        // Un puls de 250 bpm este imposibil biologic și trebuie să declanșeze excepția customizată
        new Vitals(250, 120);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeVitalsValidation() {
        // Validare de bază: semnele vitale nu pot fi negative
        new Vitals(-5, 120);
    }
}