package com.medicalapp.service;

import com.medicalapp.model.Patient;
import com.medicalapp.model.MedicalData;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

    public Optional<Integer> getMaxPulse(List<Patient> list) {
        return list.stream()
                .flatMap(p -> p.getHistory().stream())
                .map(MedicalData::getPulse)
                .max(Integer::compareTo);
    }

    public IntSummaryStatistics getStats(Patient p) {
        return p.getHistory().stream()
                .mapToInt(MedicalData::getPulse)
                .summaryStatistics();
    }

    public Map<String, List<Patient>> groupByAge(Collection<Patient> list) {
        return list.stream()
                .collect(Collectors.groupingBy(p -> p.getAge() >= 60 ? "OLD_AGE_RISK" : "YOUNG_AGE_STABLE"));
    }
}