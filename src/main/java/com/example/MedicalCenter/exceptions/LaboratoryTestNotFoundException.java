package com.example.MedicalCenter.exceptions;

public class LaboratoryTestNotFoundException extends  RuntimeException {
    public LaboratoryTestNotFoundException(String message) {
        super(message);
    }
}
