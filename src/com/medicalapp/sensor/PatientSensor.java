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

    public PatientSensor(Patient patient, HospitalData hospitalData) {
        this.patient = patient;
        this.hospitalData = hospitalData;
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
        try {
            MedicalData data = readData();
            patient.addData(data);

            if (patient.isConditionDangerous(data)) {
                MedicineResult medicine = MedicineService.masterCheck.check(data);
                String text = String.format("ALERT FOR %s (%s) -> Action: %s [Values: %s]",
                        patient.getName(), getName(), medicine, data);
                hospitalData.addAlert(text);
            }
        } catch (Exception e) {
            System.err.println("Sensor error: " + e.getMessage());
        }
    }
}