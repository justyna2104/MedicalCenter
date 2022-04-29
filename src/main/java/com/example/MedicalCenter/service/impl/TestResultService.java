package com.example.MedicalCenter.service.impl;

import com.example.MedicalCenter.exceptions.LaboratoryTestNotFoundException;
import com.example.MedicalCenter.exceptions.PatientNotFoundException;
import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.TestResult;
import com.example.MedicalCenter.repo.LaboratoryTestRepository;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.repo.TestResultRepository;
import com.example.MedicalCenter.service.ITestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TestResultService implements ITestResultService {

    private final static Logger LOGGER = Logger.getLogger(TestResultService.class.getName());

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private LaboratoryTestRepository laboratoryTestRepository;

    @Override
    public void addTestResult(long patientId, long laboratoryTestId, String description){
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("There is no patient with id: " + patientId));
        LaboratoryTest laboratoryTest = laboratoryTestRepository.findById(laboratoryTestId).orElseThrow(() -> new LaboratoryTestNotFoundException("There is no laboratory test with id: " + laboratoryTestId));

            TestResult testResult = new TestResult(description, patient, laboratoryTest);
            testResultRepository.save(testResult);
            LOGGER.info("Test result has been added to database");
    }

    @Override
    public void updateTestResult(long testResultId, String description){
        testResultRepository.findById(testResultId).ifPresentOrElse(testResult -> {
            testResult.setDescription(description);
            testResultRepository.save(testResult);
        }, () -> {throw new PatientNotFoundException("There is no test result with id: " + testResultId);});
    }

    @Override
    public void deleteTestResult(long id){
        testResultRepository.findById(id).ifPresentOrElse(testResult -> {
            testResultRepository.delete(testResult);
        }, () -> LOGGER.info("There's no test result with id " + id));
    }
}
