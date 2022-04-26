package com.example.MedicalCenter.api;

import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.TestResult;
import com.example.MedicalCenter.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
@CrossOrigin
public class TestResultController {

    @Autowired
    private TestResultService testResultService;

    @PostMapping("/addTestResult")
    public void addTestResult(@RequestParam(name = "patientId") long patientID, @RequestParam(name = "laboratoryTestId") long laboratoryTest, @RequestBody TestResult testResult){
        testResultService.addTestResult(patientID, laboratoryTest, testResult.getDescription());
    }

    @PostMapping("/updateTestResult")
    public void updateTestResult(@RequestParam(name = "testResultId") long testResultId, @RequestBody TestResult testResult){
        testResultService.updateTestResult(testResultId,  testResult.getDescription());
    }

    @PostMapping("/deleteTestResult")
    public void deleteTestResult(@RequestParam(name="id") long id){
        testResultService.deleteTestResult(id);
    }
}
