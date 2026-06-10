package com.medicalapp.tests;

import com.medicalapp.model.Inpatient;
import com.medicalapp.model.Patient;
import com.medicalapp.model.Vitals;
import com.medicalapp.service.ReportService;
import org.junit.Test;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.junit.Assert.*;

public class Test4 {

    @Test
    public void testAdvancedStreamsReports() {
        ReportService reportService = new ReportService();

        Patient p1 = new Inpatient("P-1", "Pacient Senior", 70, "101");
        Patient p2 = new Inpatient("P-2", "Pacient Tanar", 25, "102");

        p1.addVitals(new Vitals(80, 120));
        p1.addVitals(new Vitals(115, 120)); // Puls maxim absolut = 115

        p2.addVitals(new Vitals(70, 120));
        p2.addVitals(new Vitals(90, 120));

        List<Patient> hospital = Arrays.asList(p1, p2);

        Optional<Integer> maxHeartRate = reportService.getAbsoluteMaxHeartRate(hospital);
        assertTrue(maxHeartRate.isPresent());
        assertEquals(Integer.valueOf(115), maxHeartRate.get());

        IntSummaryStatistics stats = reportService.getHeartRateStatsForPatient(p1);
        assertEquals(2, stats.getCount());
        assertEquals(115, stats.getMax());
        assertEquals(80, stats.getMin());

        Map<String, List<Patient>> demographics = reportService.groupPatientsByAgeDemographics(hospital);
        assertEquals(1, demographics.get("SENIORI_RISC_CRESCUT").size());
        assertEquals(1, demographics.get("ADULTI_STABIL").size());
    }
}