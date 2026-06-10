package com.medicalapp.tests;

import com.medicalapp.exception.WrongFormatException;
import com.medicalapp.exception.DeviceErrorException;
import com.medicalapp.model.PatientCritical;
import com.medicalapp.model.PatientNormal;
import com.medicalapp.model.Patient;
import com.medicalapp.repository.HospitalData;
import org.junit.Before;
import org.junit.Test;
import java.util.Collection;
import static org.junit.Assert.*;

public class Test1 {
    private HospitalData hospital;
    private Patient p1;
    private Patient p2;

    @Before
    public void setUp() {
        hospital = new HospitalData();
        p1 = new PatientCritical("P-001", "Gheorghe", 75, 12);
        p2 = new PatientNormal("P-002", "Elena", 25, "4A");
        hospital.addPatient(p1);
        hospital.addPatient(p2);
    }

    @Test
    public void testPatientsAndRepository() {
        assertTrue(p1.getType().contains("CRITICAL"));
        assertTrue(p2.getType().contains("NORMAL"));
        assertEquals(12, ((PatientCritical) p1).getBedNumber());
        assertEquals("4A", ((PatientNormal) p2).getRoomNumber());
        assertEquals("P-001", p1.getId());

        Patient found = hospital.findPatient("P-001");
        assertNotNull(found);
        Collection<Patient> all = hospital.getAll();
        assertEquals(2, all.size());
    }

    @Test
    public void testExceptionsData() {
        WrongFormatException ex1 = new WrongFormatException("BAD_TEXT", "Format error");
        DeviceErrorException ex2 = new DeviceErrorException("DEV-101", ex1);
        assertEquals("BAD_TEXT", ex1.getRawData());
        assertEquals("DEV-101", ex2.getDeviceId());
    }
}