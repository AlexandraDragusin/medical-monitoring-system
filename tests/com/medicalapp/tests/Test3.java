package com.medicalapp.tests;

import com.medicalapp.model.MedicalData;
import com.medicalapp.service.MedicineService;
import com.medicalapp.service.MedicineResult;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3 {

    @Test
    public void testLambdas() {
        MedicalData highBp = new MedicalData(70, 160);
        MedicineResult r1 = MedicineService.masterCheck.check(highBp);
        assertTrue(r1.isUrgent());
        assertEquals("ACE-Inhibitor", r1.getName());

        MedicalData highPulse = new MedicalData(120, 110);
        MedicineResult r2 = MedicineService.masterCheck.check(highPulse);
        assertTrue(r2.isUrgent());
        assertEquals("Beta-Blocker", r2.getName());

        assertNotNull(highPulse.toString());
        assertNotNull(highPulse.getTime());
    }

    @Test
    public void testUtility() {
        assertTrue(MedicineService.isSystemBusy(150));
        assertFalse(MedicineService.isSystemBusy(20));
    }
}