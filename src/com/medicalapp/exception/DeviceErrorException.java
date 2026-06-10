package com.medicalapp.exception;

public class DeviceErrorException extends Exception {
    private final String deviceId;

    public DeviceErrorException(String deviceId, Throwable cause) {
        super("Device " + deviceId + " is broken!", cause);
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}