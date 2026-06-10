package com.medicalapp.exception;

public class PatientErrorException extends RuntimeException {
    private final String patientId;

    public PatientErrorException(String patientId, String message) {
        super(message);
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }
}