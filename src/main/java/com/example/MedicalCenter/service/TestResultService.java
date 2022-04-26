package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.TestResult;
import com.example.MedicalCenter.repo.LaboratoryTestRepository;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.repo.TestResultRepository;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TestResultService {

    private final static Logger LOGGER = Logger.getLogger(TestResultService.class.getName());

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private LaboratoryTestRepository laboratoryTestRepository;


    public void addTestResult(long patienId, long laboratoryTestId, String description){
        Optional<Patient> patient = patientRepository.findById(patienId);
        Optional<LaboratoryTest> laboratoryTest = laboratoryTestRepository.findById(laboratoryTestId);

        if(patient.isPresent() && laboratoryTest.isPresent()){
            TestResult testResult = new TestResult(description, patient.get(), laboratoryTest.get());
            testResultRepository.save(testResult);
            LOGGER.info("Test result has been added to database");
        }else LOGGER.info("Test result could not have been added");
    }

    public void updateTestResult(long testResultId, String description){
        Optional<TestResult> testResult = testResultRepository.findById(testResultId);
        if(testResult.isPresent()){
            testResult.get().setDescription(description);
            testResultRepository.save(testResult.get());
        }else LOGGER.info("Could not update test result");
    }

    public void deleteTestResult(long id){
        Optional<TestResult> testResult = testResultRepository.findById(id);
        if(testResult.isPresent()){
            testResultRepository.delete(testResult.get());
            LOGGER.info("Test result with id " + id + " has been removed");
        }else LOGGER.info("There's no test result with id " + id);
    }
}
