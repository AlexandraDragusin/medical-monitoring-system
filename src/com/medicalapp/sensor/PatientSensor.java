package com.medicalapp.sensor;

import com.medicalapp.model.Patient;
import com.medicalapp.model.MedicalData;
import com.medicalapp.repository.HospitalData;
import com.medicalapp.service.MedicineService;
import com.medicalapp.service.MedicineResult;
import java.util.Random;

public class PatientSensor implements Runnable {
    private final Patient patient;
    private final HospitalData hospitalData;
    private final Random random = new Random();

    public PatientSensor(Patient patient, HospitalData hospitalData) {
        this.patient = patient;
        this.hospitalData = hospitalData;
    }

    @Override
    public void run() {
        try {
            int pulse = 65 + random.nextInt(55);
            int pressure = 115 + random.nextInt(45);

            MedicalData data = new MedicalData(pulse, pressure);
            patient.addData(data);

            MedicineResult result = MedicineService.masterCheck.check(data);

            if (result.isUrgent()) {
                String text = String.format("ALERT FOR %s -> %s [Values: %s]",
                        patient.getName(), result, data);
                hospitalData.addAlert(text);
            }
        } catch (Exception e) {
            System.err.println("Sensor error: " + e.getMessage());
        }
    }
}