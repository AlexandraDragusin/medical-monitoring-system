package com.medicalapp.tests;

import com.medicalapp.model.PatientNormal;
import com.medicalapp.model.Patient;
import com.medicalapp.model.MedicalData;
import com.medicalapp.service.ReportService;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.junit.Assert.*;

public class Test4 {

    @Test
    public void testStreams() {
        ReportService reportService = new ReportService();
        Patient p1 = new PatientNormal("P-1", "Senior", 70, "101");
        Patient p2 = new PatientNormal("P-2", "Young", 25, "102");

        p1.addData(new MedicalData(80, 120));
        p1.addData(new MedicalData(115, 120));
        p2.addData(new MedicalData(70, 120));

        List<Patient> list = Arrays.asList(p1, p2);

        Optional<Integer> maxPulse = reportService.getMaxPulse(list);
        assertTrue(maxPulse.isPresent());
        assertEquals(Integer.valueOf(115), maxPulse.get());

        Optional<Integer> emptyMax = reportService.getMaxPulse(new ArrayList<>());
        assertFalse(emptyMax.isPresent());

        IntSummaryStatistics stats = reportService.getStats(p1);
        assertEquals(2, stats.getCount());
        assertEquals(115, stats.getMax());

        Map<String, List<Patient>> demographics = reportService.groupByAge(list);
        assertEquals(1, demographics.get("OLD_AGE_RISK").size());
        assertEquals(1, demographics.get("YOUNG_AGE_STABLE").size());
    }
}