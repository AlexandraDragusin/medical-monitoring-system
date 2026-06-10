package com.medicalapp.tests;

import com.medicalapp.model.PatientCritical;
import com.medicalapp.model.Patient;
import com.medicalapp.repository.HospitalData;
import com.medicalapp.sensor.AlertManager;
import com.medicalapp.sensor.PatientSensor;
import com.medicalapp.sensor.DevicePulse;
import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class Test5 {

    @Test
    public void testAsyncSystem() throws InterruptedException {
        HospitalData hospital = new HospitalData();
        Patient patient = new PatientCritical("P-T", "Test", 45, 1);
        hospital.addPatient(patient);

        AlertManager alertManager = new AlertManager(hospital);
        PatientSensor sensor = new PatientSensor(patient, hospital);
        DevicePulse devPulse = new DevicePulse(patient, hospital);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(alertManager);
        executor.submit(sensor);
        executor.submit(devPulse);

        Thread.sleep(1100);

        alertManager.stop();
        executor.shutdown();
        executor.awaitTermination(2, TimeUnit.SECONDS);

        assertFalse(patient.getHistory().isEmpty());
    }

    @Test
    public void testMainCoverage() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                com.medicalapp.Main.main(new String[]{});
            } catch (InterruptedException e) {
                // Done
            }
        });
        thread.start();
        Thread.sleep(1500);
        thread.interrupt();
    }
}