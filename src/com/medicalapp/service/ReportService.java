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
                .reduce(Integer::max);
    }

    public IntSummaryStatistics getStats(Patient p) {
        return p.getHistory().stream()
                .collect(Collectors.summarizingInt(MedicalData::getPulse));
    }

    public Map<String, List<Patient>> groupByAge(Collection<Patient> list) {
        return list.stream()
                .collect(Collectors.groupingBy(p -> {
                    if (p.getAge() >= 60) return "OLD_AGE_RISK";
                    return "YOUNG_AGE_STABLE";
                }));
    }
}