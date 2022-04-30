package com.example.MedicalCenter.service.impl;

import com.example.MedicalCenter.exceptions.ConsentNotFoundException;
import com.example.MedicalCenter.exceptions.PatientNotFoundException;
import com.example.MedicalCenter.exceptions.ResearchProjectNotFoundException;
import com.example.MedicalCenter.model.*;
import com.example.MedicalCenter.repo.*;
import com.example.MedicalCenter.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;



@Service
public class PatientService implements IPatientService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PersonalDataRepository personalDataRepository;

    @Autowired
     private ConsentRepository consentRepository;

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    @Autowired
    private ConsentService consentService;

    @Autowired
    private LaboratoryTestRepository laboratoryTestRepository;

    @Autowired TestResultService testResultService;

    @Override
    public void registerPatient(PersonalData personalData) {
        Patient patient = new Patient(personalData);
        patientRepository.save(patient);
    }

    @Override
    public void updatePatientPersonalData(long id, PersonalData personalData) {
        patientRepository.findById(id).ifPresentOrElse(patient -> {
            patient.setPersonalData(personalData);
            patientRepository.save(patient);
        }, () -> {throw new PatientNotFoundException("There is no patient with id: " + id);});
    }

    @Override
    public PersonalData getPatientsPersonalData(long id){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("There is no patient with id: " + id));
        return patient.getPersonalData();
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    @Override
    public void deletePatient(long id) {
        patientRepository.findById(id).ifPresentOrElse(patient -> {
            PersonalData personalData = patient.getPersonalData();
            personalDataRepository.delete(personalData);

            for(Consent c : patient.getConsents()){
                consentService.withdrawConsent(c.getId());
            }

            for(TestResult tr : patient.getTestResults()){
                testResultService.deleteTestResult(tr.getId());
            }

            for(LaboratoryTest lt : patient.getLaboratoryTests()){
                laboratoryTestRepository.delete(lt);
            }

            patientRepository.delete(patient);
        }, () -> LOGGER.info("There's no patient with id " + id));
    }

    @Override
    public void bindPatientWithResearchProject(long patientId, long researchProjectId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("There is no patient with id: " + patientId));
        ResearchProject researchProject = researchProjectRepository.findById(researchProjectId).orElseThrow(() -> new ResearchProjectNotFoundException("There is no research project with id: " + researchProjectId));

        consentRepository.findConsentByPatientAndResearchProject(patient, researchProject)
                .ifPresentOrElse(consent -> {
                    researchProject.getPatients().add(patient);
                    researchProjectRepository.save(researchProject);
                    LOGGER.info("Patient has been bind with research project");
                }, () -> {
                    LOGGER.warn("There is no consent to bind patient with this research project");
                    throw new ConsentNotFoundException("Patient has not given consent");
                });
    }

    @Transactional
    @Override
    public void unbindPatientWithResearchProject(long patientId, long researchProjectId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("There is no patient with id: " + patientId));
        ResearchProject researchProject = researchProjectRepository.findById(researchProjectId).orElseThrow(() -> new ResearchProjectNotFoundException("There is no research project with id: " + researchProjectId));
        researchProject.getPatients().remove(patient);
        patient.getResearchProjects().remove(researchProject);
        researchProjectRepository.save(researchProject);
        patientRepository.save(patient);
        LOGGER.info("The patient has been removed form research project");
    }
}
