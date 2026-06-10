package com.medicalapp.sensor;

import com.medicalapp.repository.HospitalData;
import java.util.concurrent.TimeUnit;

public class AlertManager implements Runnable {
    private final HospitalData hospitalData;
    private volatile boolean active = true;

    public AlertManager(HospitalData hospitalData) {
        this.hospitalData = hospitalData;
    }

    public void stop() {
        this.active = false;
    }

    @Override
    public void run() {
        System.out.println("[ALERTS] Alarm system is active...");
        while (active || !hospitalData.getAlerts().isEmpty()) {
            try {
                String msg = hospitalData.getAlerts().poll(1, TimeUnit.SECONDS);
                if (msg != null) {
                    System.out.println("\n EMERGENCY: " + msg);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[ALERTS] Alarm system stopped.");
    }
}