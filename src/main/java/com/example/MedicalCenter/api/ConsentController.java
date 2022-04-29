package com.example.MedicalCenter.api;

import com.example.MedicalCenter.service.impl.ConsentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/uploadImage")
    public void uploadImage(@RequestParam(name = "consentId") long id, @RequestBody MultipartFile multipartFile, @RequestParam String fileName){
        consentService.uploadImage(id, multipartFile, fileName);
    }

    @PostMapping("/deleteImage")
    public void deleteImage(@RequestParam(name="consentId") long id){
        consentService.deleteImage(id);
    }

    @GetMapping("/downloadImage")
    public Resource downloadImage(@RequestParam(name="consentId") long id){
        return consentService.downloadImage(id);
    }
}
