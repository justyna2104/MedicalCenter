package com.example.MedicalCenter.api;

import com.example.MedicalCenter.model.TestResult;
import com.example.MedicalCenter.service.impl.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@CrossOrigin
public class TestResultController {

    @Autowired
    private TestResultService testResultService;

    @PostMapping("/addTestResult")
    public void addTestResult(@RequestParam(name = "patientId") long patientID, @RequestParam(name = "laboratoryTestId") long laboratoryTest, @RequestBody String description){
        testResultService.addTestResult(patientID, laboratoryTest, description);
    }

    @PostMapping("/updateTestResult")
    public void updateTestResult(@RequestParam(name = "testResultId") long testResultId, @RequestBody String testResult){
        testResultService.updateTestResult(testResultId,  testResult);
    }

    @PostMapping("/deleteTestResult")
    public void deleteTestResult(@RequestParam(name="id") long id){
        testResultService.deleteTestResult(id);
    }
}
