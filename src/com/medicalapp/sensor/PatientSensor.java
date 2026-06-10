package com.medicalapp.sensor;

import com.medicalapp.model.Patient;
import com.medicalapp.model.MedicalData;
import com.medicalapp.repository.HospitalData;
import com.medicalapp.service.MedicineService;
import com.medicalapp.service.MedicineResult;
import java.util.Random;

public class PatientSensor implements Device {
    private final Patient patient;
    private final HospitalData hospitalData;
    private final Random random = new Random();
    private volatile boolean active = true;

    public PatientSensor(Patient patient, HospitalData hospitalData) {
        this.patient = patient;
        this.hospitalData = hospitalData;

        new Thread(this).start();
    }

    public void stop() {
        this.active = false;
    }

    @Override
    public MedicalData readData() {
        int pulse = 65 + random.nextInt(55);
        int pressure = 115 + random.nextInt(45);
        return new MedicalData(pulse, pressure);
    }

    @Override
    public String getName() {
        return "Bedside-Monitor-v2";
    }

    @Override
    public void run() {
        System.out.println("[SENSOR] Started monitoring for patient: " + patient.getName());

        while (active) {
            try {
                int pulse = 65 + random.nextInt(55);
                int pressure = 110 + random.nextInt(50);

                MedicalData data = new MedicalData(pulse, pressure);
                patient.addData(data);

                if (patient.isConditionDangerous(data)) {
                    MedicineResult medicine = MedicineService.masterCheck.check(data);

                    String text = String.format("ALERT FOR %s (%s) -> Action: %s [Values: %s]",
                            patient.getName(), "Bedside-Monitor", medicine, data);

                    hospitalData.addAlert(text);
                }

                Thread.sleep(1500);

            } catch (Exception e) {
                break;
            }
        }
        System.out.println("[SENSOR] Stopped monitoring for patient: " + patient.getName());
    }
}