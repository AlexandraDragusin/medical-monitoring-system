package com.medicalapp.sensor;

import com.medicalapp.repository.HospitalData;
import java.util.concurrent.TimeUnit;

public class AlertManager implements Runnable {
    private final HospitalData hospitalData;

    // volatile face ca modificarea variabilei sa fie vazuta instantaneu de toate thread-urile care o acceseaza
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
                if (active && hospitalData.getAlerts().isEmpty()) {
                    Thread.sleep(100);

                    if (!active) break;
                }

                // daca coada nu e goala, preluam si afisam mesajul de alerta
                if (!hospitalData.getAlerts().isEmpty()) {
                    String msg = hospitalData.getAlerts().take();

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