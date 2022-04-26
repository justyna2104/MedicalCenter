package com.example.MedicalCenter.api;

import com.example.MedicalCenter.service.ConsentService;
import com.example.MedicalCenter.service.ResearchProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ConsentController {

    @Autowired
    private ConsentService consentService;

    @PostMapping("/addConsent")
    public void addConsent(@RequestParam(name = "patientId") long patientID, @RequestParam(name = "researchProjectId") long researchProjectId){
        consentService.addConsent(patientID, researchProjectId);
    }

    @PostMapping("/withdrawConsent")
    public void withdrawConsent(@RequestParam(name = "id") long id){
        consentService.withdrawConsent(id);
    }
}
