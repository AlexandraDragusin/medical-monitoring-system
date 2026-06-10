package com.medicalapp.service;

import com.medicalapp.model.Patient;
import com.medicalapp.model.Vitals;
import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

    /**
     * Găsește cel mai mare puls înregistrat vreodată în spital folosind .reduce() custom
     */
    public Optional<Integer> getAbsoluteMaxHeartRate(List<Patient> patients) {
        return patients.stream()
                .flatMap(p -> p.getVitalsHistory().stream())
                .map(Vitals::getHeartRate)
                .reduce(Integer::max); // Utilizare avansată reduce
    }

    /**
     * Generează statistici medicale complete (Medie, Minim, Maxim, Număr probe) pentru pulsul unui pacient
     */
    public IntSummaryStatistics getHeartRateStatsForPatient(Patient patient) {
        return patient.getVitalsHistory().stream()
                .collect(Collectors.summarizingInt(Vitals::getHeartRate)); // Summary statistics
    }

    /**
     * Grupează pacienții pe categorii de risc în funcție de vârstă (Tineri vs Seniori)
     */
    public Map<String, List<Patient>> groupPatientsByAgeDemographics(Collection<Patient> patients) {
        return patients.stream()
                .collect(Collectors.groupingBy(p -> {
                    if (p.getAge() >= 60) return "SENIORI_RISC_CRESCUT";
                    return "ADULTI_STABIL";
                }));
    }
}