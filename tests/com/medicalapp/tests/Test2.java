package com.medicalapp.tests;

import com.medicalapp.exception.BadDataException;
import com.medicalapp.exception.PatientErrorException;
import com.medicalapp.model.MedicalData;
import com.medicalapp.repository.HospitalData;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test2 {

    @Test
    public void testPatientNotFoundException() {
        HospitalData hospital = new HospitalData();
        try {
            hospital.findPatient("GHOST");
            fail("Expected PatientErrorException");
        } catch (PatientErrorException e) {
            assertEquals("GHOST", e.getPatientId());
        }
    }

    @Test
    public void testBadDataException() {
        try {
            new MedicalData(250, 120);
            fail("Expected BadDataException");
        } catch (BadDataException e) {
            assertEquals(250, e.getPulse());
            assertEquals(120, e.getPressure());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeValues() {
        new MedicalData(-5, 120);
    }
}