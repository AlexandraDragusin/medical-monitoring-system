package com.medicalapp.sensor;

import com.medicalapp.repository.HospitalRepository;
import java.util.concurrent.TimeUnit;

public class EmergencyDispatcher implements Runnable {
    private final HospitalRepository repository;
    private volatile boolean active = true;

    public EmergencyDispatcher(HospitalRepository repository) {
        this.repository = repository;
    }

    public void stopDispatcher() {
        this.active = false;
    }

    @Override
    public void run() {
        System.out.println("[DISPATCHER] Centrul de primiri urgențe este activ și ascultă alertele...");
        while (active || !repository.getEmergencyAlertsQueue().isEmpty()) {
            try {
                String alert = repository.getEmergencyAlertsQueue().poll(1, TimeUnit.SECONDS);
                if (alert != null) {
                    System.out.println("\n DISPATCHER TELEMETRIE: " + alert);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[DISPATCHER] Centrul de urgențe a fost oprit.");
    }
}