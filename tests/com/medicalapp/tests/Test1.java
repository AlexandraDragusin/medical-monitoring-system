package com.medicalapp.tests;

import com.medicalapp.exception.WrongFormatException;
import com.medicalapp.exception.DeviceErrorException;
import com.medicalapp.model.PatientCritical;
import com.medicalapp.model.PatientNormal;
import com.medicalapp.model.Patient;
import com.medicalapp.model.MedicalData;
import com.medicalapp.repository.HospitalData;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test1 {
    private HospitalData hospital;
    private Patient p1;
    private Patient p2;

    @Before
    public void setUp() {
        hospital = new HospitalData();
        p1 = new PatientCritical("P-001", "Gheorghe", 75, 12); // Critic, 75 ani
        p2 = new PatientNormal("P-002", "Elena", 25, "4A");     // Normal, 25 ani
        hospital.addPatient(p1);
        hospital.addPatient(p2);
    }

    @Test
    public void testPatientsPolymorphicRiskAndRepository() {
        MedicalData borderData = new MedicalData(100, 138);

        assertTrue(p1.isConditionDangerous(borderData));
        assertFalse(p2.isConditionDangerous(borderData));

        assertEquals(12, ((PatientCritical) p1).getBedNumber());
        assertEquals("4A", ((PatientNormal) p2).getRoomNumber());

        Patient found = hospital.findPatient("P-001");
        assertNotNull(found);
    }

    @Test
    public void testExceptionsData() {
        WrongFormatException ex1 = new WrongFormatException("BAD_TEXT", "Format error");
        DeviceErrorException ex2 = new DeviceErrorException("DEV-101", ex1);

        assertEquals("BAD_TEXT", ex1.getRawData());
        assertEquals("DEV-101", ex2.getDeviceId());
    }
}