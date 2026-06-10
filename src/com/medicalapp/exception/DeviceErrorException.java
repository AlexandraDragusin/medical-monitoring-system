package com.medicalapp.exception;

// Clasa Exception ne obliga sa folosim try-catch sau sa declaram throws in semnatura metodei
public class DeviceErrorException extends Exception {
    // ID-ul senzorului care s-a stricat
    private final String deviceId;

    // Exception chaining - putem sa pasam cauza acestei exceptii (de exemplu, o alta exceptie care a fost aruncata inainte)
    public DeviceErrorException(String deviceId, Throwable cause) {
        super("Device " + deviceId + " is broken!", cause);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}