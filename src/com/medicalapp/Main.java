package com.medicalapp;

import com.medicalapp.model.Patient;
import com.medicalapp.model.PatientCritical;
import com.medicalapp.model.PatientNormal;
import com.medicalapp.repository.HospitalData;
import com.medicalapp.sensor.AlertManager;
import com.medicalapp.sensor.PatientSensor;
import com.medicalapp.service.ReportService;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== START HOSPITAL MONITORING SYSTEM ===");

        HospitalData hospital = new HospitalData();
        ReportService reportService = new ReportService();

        Patient p1 = new PatientCritical("P-901", "Vasile Gherman", 72, 5);
        Patient p2 = new PatientNormal("P-902", "Elena Radu", 29, "302");

        hospital.addPatient(p1);
        hospital.addPatient(p2);

        AlertManager alerts = new AlertManager(hospital);
        ExecutorService dispatcherExecutor = Executors.newSingleThreadExecutor();
        dispatcherExecutor.submit(alerts);

        ScheduledExecutorService sensorScheduler = Executors.newScheduledThreadPool(2);
        sensorScheduler.scheduleAtFixedRate(new PatientSensor(p1, hospital), 0, 1500, TimeUnit.MILLISECONDS);
        sensorScheduler.scheduleAtFixedRate(new PatientSensor(p2, hospital), 0, 1500, TimeUnit.MILLISECONDS);

        Thread.sleep(5000);

        System.out.println("\n--- STOPPING SYSTEM ---");
        sensorScheduler.shutdown();
        sensorScheduler.awaitTermination(3, TimeUnit.SECONDS);

        alerts.stop();
        dispatcherExecutor.shutdown();
        dispatcherExecutor.awaitTermination(3, TimeUnit.SECONDS);

        System.out.println("\n=== HOSPITAL REPORT ===");
        List<Patient> list = Arrays.asList(p1, p2);

        reportService.getMaxPulse(list)
                .ifPresent(max -> System.out.println("Max pulse found today: " + max + " bpm"));

        double averagePulse = reportService.getAveragePulse(p1);
        System.out.println("Average pulse for " + p1.getName() + " -> Average Pulse: " + averagePulse + " bpm");

        System.out.println("\nDemographics:");
        reportService.groupByAge(hospital.getAll())
                .forEach((group, patients) -> System.out.println(" -> " + group + ": " + patients.size() + " patients"));
    }
}