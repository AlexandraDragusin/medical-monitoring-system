package com.medicalapp.exception;

public class WrongFormatException extends Exception {
    private static final long serialVersionUID = 1L;
    private final String rawData;

    public WrongFormatException(String rawData, String message) {
        super(message);
        this.rawData = rawData;
    }

    public String getRawData() { return rawData; }
}