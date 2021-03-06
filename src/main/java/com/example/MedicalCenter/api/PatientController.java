package com.example.MedicalCenter.api;

import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.PersonalData;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/registerPatient")
    public void registerPatient(@RequestBody PersonalData personalData){
        patientService.registerPatient(personalData);
    }

    @PostMapping("/updatePatientPersonalData")
    public void updatePatientPeraonalData(@RequestParam(name = "id") long id, @RequestBody PersonalData personalData){
        patientService.updatePatientPersonalData(id, personalData);
    }

    @GetMapping("/getPatientPersonalData")
    public PersonalData getPatientPesronalData(@RequestParam(name ="id")  long id){
       return patientService.getPatientsPersonalData(id);
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }


    @PostMapping("/bindPatientWithResearchProject")
    public void bindPatientWithResearchProject(@RequestParam(name = "patientId") long patientId, @RequestParam(name = "researchProjectId") long researchProjectId){
        patientService.bindPatientWithResearchProject(patientId, researchProjectId);
    }

    @PostMapping("/unbindPatientWithResearchProject")
    public void unbindPatientWithResearchProject(@RequestParam(name = "patientId") long patientId, @RequestParam(name = "researchProjectId") long researchProjectId){
        patientService.unbindPatientWithResearchProject(patientId, researchProjectId);
    }

    @PostMapping("/deletePatient")
    public void deletePatient(@RequestParam(name = "id") long id){
        patientService.deletePatient(id);
    }
}
