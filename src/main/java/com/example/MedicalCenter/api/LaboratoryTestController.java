package com.example.MedicalCenter.api;

import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.service.impl.LaboratoryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
@CrossOrigin
public class LaboratoryTestController {

    @Autowired
    private LaboratoryTestService laboratoryTestService;

    @PostMapping("/commissionLaboratoryTest")
    public void commissionLaboratoryTest(@RequestParam(name = "patientId") long patientID, @RequestParam(name = "researchProjectId") long researchProjectId, @RequestBody LocalDateTime dateAndTime){
        laboratoryTestService.commissionLaboratoryTest(patientID, researchProjectId, dateAndTime);
    }
}
