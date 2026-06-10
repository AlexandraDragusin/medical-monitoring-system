package com.medicalapp.tests;

import com.medicalapp.model.Vitals;
import com.medicalapp.service.DosageService;
import com.medicalapp.service.MedicalPrescription;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test3 {

    @Test
    public void testChainedLambdaRules() {
        Vitals highBp = new Vitals(75, 160);
        MedicalPrescription rx1 = DosageService.masterHospitalRule.analyze(highBp);

        assertTrue(rx1.isUrgent());
        assertEquals("Inhibitor ACE", rx1.getMedication());
        assertEquals(20, rx1.getDosageMg());
        assertNotNull(rx1.toString());

        Vitals highPulse = new Vitals(120, 115);
        MedicalPrescription rx2 = DosageService.masterHospitalRule.analyze(highPulse);

        assertTrue(rx2.isUrgent());
        assertEquals("Beta-Blocant", rx2.getMedication());

        // Linie de acoperire pentru getter-ul de timestamp din Vitals și toString
        assertNotNull(highPulse.getTimestamp());
        assertNotNull(highPulse.toString());
    }

    @Test
    public void testDosageServiceUtilityMethods() {
        // Acoperim metoda statică de utilitate din DosageService
        assertTrue(DosageService.isSystemOverloaded(105));
        assertFalse(DosageService.isSystemOverloaded(50));
    }
}