package com.medicalapp.service;

import com.medicalapp.model.Patient;
import com.medicalapp.model.MedicalData;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

    public Optional<Integer> getMaxPulse(List<Patient> list) {
        return list.stream()
                .flatMap(patient -> patient.getHistory().stream())
                .map(MedicalData::getPulse)
                .max(Integer::compareTo);
    }

    public double getAveragePulse(Patient p) {
        return p.getHistory().stream()
                .mapToDouble(MedicalData::getPulse)
                .average()
                .orElse(0.0);
    }

    public Map<String, List<Patient>> groupByAge(Collection<Patient> list) {
        return list.stream()
                .collect(Collectors.groupingBy(p -> p.getAge() >= 60 ? "OLD_AGE_RISK" : "YOUNG_AGE_STABLE"));
        // groupingBy returneaza un map unde cheia este rezultatul functiei de clasificare, iar valoarea este o lista cu pacientii care se incadreaza in acea categorie
    }
}