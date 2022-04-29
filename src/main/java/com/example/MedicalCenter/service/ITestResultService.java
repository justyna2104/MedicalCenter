package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.TestResult;

import java.util.Optional;

public interface ITestResultService {

    void addTestResult(long patienId, long laboratoryTestId, String description);

    void updateTestResult(long testResultId, String description);

    void deleteTestResult(long id);
}
