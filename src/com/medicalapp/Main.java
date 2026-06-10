package com.medicalapp;

import com.medicalapp.model.IcuPatient;
import com.medicalapp.model.Inpatient;
import com.medicalapp.model.Patient;
import com.medicalapp.repository.HospitalRepository;
import com.medicalapp.sensor.EmergencyDispatcher;
import com.medicalapp.sensor.PatientMonitorSensor;
import com.medicalapp.service.ReportService;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== SISTEM ENTERPRISE DE MONITORIZARE MEDICALA HIERARHICA ===");

        // 1. Inițializare infrastructură date
        HospitalRepository hospital = new HospitalRepository();
        ReportService reportService = new ReportService();

        Patient vasile = new IcuPatient("P-901", "Vasile Gherman", 72, 5);
        Patient elena = new Inpatient("P-902", "Elena Radu", 29, "Salon 302");

        hospital.registerPatient(vasile);
        hospital.registerPatient(elena);

        // 2. Pornire Dispatcher Alerte Urgență pe fir de execuție separat
        EmergencyDispatcher dispatcher = new EmergencyDispatcher(hospital);
        ExecutorService dispatcherExecutor = Executors.newSingleThreadExecutor();
        dispatcherExecutor.submit(dispatcher);

        // 3. Creare Scheduler Pool pentru Senzori (Simulăm transmiterea periodică)
        ScheduledExecutorService sensorScheduler = Executors.newScheduledThreadPool(2);

        // Planificăm senzorii să ruleze la fiecare 1.5 secunde, automat
        sensorScheduler.scheduleAtFixedRate(new PatientMonitorSensor(vasile, hospital), 0, 1500, TimeUnit.MILLISECONDS);
        sensorScheduler.scheduleAtFixedRate(new PatientMonitorSensor(elena, hospital), 0, 1500, TimeUnit.MILLISECONDS);

        // Lăsăm spitalul să funcționeze live timp de 6 secunde
        Thread.sleep(6000);

        System.out.println("\n--- OPRIRE CONTROLATA A SISTEMULUI CONCURENT ---");
        sensorScheduler.shutdown();
        sensorScheduler.awaitTermination(3, TimeUnit.SECONDS);

        // Oprim dispatcher-ul de urgențe
        dispatcher.stopDispatcher();
        dispatcherExecutor.shutdown();
        dispatcherExecutor.awaitTermination(3, TimeUnit.SECONDS);

        // 4. Generare Rapoarte Analitice cu STREAMS la final de zi
        System.out.println("\n=== RAPORT ANALITIC MANAGER SPITAL ===");
        List<Patient> patientList = Arrays.asList(vasile, elena);

        reportService.getAbsoluteMaxHeartRate(patientList)
                .ifPresent(max -> System.out.println("Cel mai mare puls critic detectat azi: " + max + " bpm"));

        IntSummaryStatistics vasileStats = reportService.getHeartRateStatsForPatient(vasile);
        System.out.println("Statistici Puls [" + vasile.getName() + "]: " +
                "Medie: " + vasileStats.getAverage() + " bpm, Maxim: " + vasileStats.getMax() + " bpm");

        System.out.println("\nDemografie Secții:");
        reportService.groupPatientsByAgeDemographics(hospital.getAllPatients())
                .forEach((grup, pacienti) -> System.out.println(" -> " + grup + ": " + pacienti.size() + " pacienți interni"));
    }
}