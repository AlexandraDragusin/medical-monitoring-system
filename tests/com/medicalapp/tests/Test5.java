package com.medicalapp.tests;

import com.medicalapp.model.IcuPatient;
import com.medicalapp.model.Patient;
import com.medicalapp.repository.HospitalRepository;
import com.medicalapp.sensor.EmergencyDispatcher;
import com.medicalapp.sensor.PatientMonitorSensor;
import com.medicalapp.sensor.PulseOximeterSensor;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Test5 {

    @Test
    public void testSensorsAndDispatcherAsynchronous() throws InterruptedException {
        HospitalRepository repository = new HospitalRepository();
        Patient patient = new IcuPatient("P-TEST", "Test Async", 45, 1);
        repository.registerPatient(patient);

        // Instanțiem și rulăm componentele din pachetul sensor pentru acoperire cod
        EmergencyDispatcher dispatcher = new EmergencyDispatcher(repository);
        PatientMonitorSensor monitorSensor = new PatientMonitorSensor(patient, repository);
        PulseOximeterSensor oximeterSensor = new PulseOximeterSensor(patient, repository);

        // Verificăm numele dispozitivului din interfață
        assertEquals("PulseOximeter-v4", oximeterSensor.getSensorDeviceName());

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(dispatcher);
        executor.submit(monitorSensor);
        executor.submit(oximeterSensor);

        // Lăsăm firele de execuție să ruleze scurt pentru a colecta coverage
        Thread.sleep(1200);

        // Oprim dispatcher-ul curat
        dispatcher.stopDispatcher();
        executor.shutdown();
        boolean finished = executor.awaitTermination(2, TimeUnit.SECONDS);

        // Validăm că s-au generat date în istoricul pacientului
        assertFalse(patient.getVitalsHistory().isEmpty());
    }

    @Test
    public void testMainAppOrchestrator() throws InterruptedException {
        // Pornim structura completă din Main într-un thread secundar timp de 2 secunde pentru a acoperi clasa Main.java
        Thread mainThread = new Thread(() -> {
            try {
                com.medicalapp.Main.main(new String[]{});
            } catch (InterruptedException e) {
                // Oprire simulată cu succes
            }
        });
        mainThread.start();
        Thread.sleep(2000);
        mainThread.interrupt();
    }
}