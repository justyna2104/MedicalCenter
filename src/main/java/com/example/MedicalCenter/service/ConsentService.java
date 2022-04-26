package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.Consent;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.repo.ConsentRepository;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.repo.ResearchProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ConsentService {

    private final static Logger LOGGER = Logger.getLogger(ConsentService.class.getName());

    @Autowired
    private ConsentRepository consentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    public void addConsent(long patientId, long researchProjectId){
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<ResearchProject> researchProject = researchProjectRepository.findById(researchProjectId);
        if(patient.isPresent() && researchProject.isPresent()){
            Consent consent = new Consent(researchProject.get(), patient.get());
            consentRepository.save(consent);
            LOGGER.info("Consent has been added");
        }else LOGGER.info("Consent could not have been added");
    }

    public void withdrawConsent(long id){
        Optional<Consent> consent = consentRepository.findById(id);
        if(consent.isPresent()){
            Optional<ResearchProject> researchProject = researchProjectRepository.findById(consent.get().getResearchProject().getId());
            Optional<Patient> patient = patientRepository.findById(consent.get().getPatient().getId());
            researchProject.get().getPatients().remove(patient.get());
            consentRepository.delete(consent.get());

            LOGGER.info("Consent with id " + id + " has been removed. Patient is no longer part of the project");
        }else LOGGER.info("There's no patient with id " + id);
    }
}
