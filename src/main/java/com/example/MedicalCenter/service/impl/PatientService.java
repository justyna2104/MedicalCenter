package com.example.MedicalCenter.service.impl;

import com.example.MedicalCenter.exceptions.ConsentNotFoundException;
import com.example.MedicalCenter.exceptions.PatientNotFoundException;
import com.example.MedicalCenter.exceptions.ResearchProjectNotFoundException;
import com.example.MedicalCenter.model.Consent;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.PersonalData;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.repo.ConsentRepository;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.repo.ResearchProjectRepository;
import com.example.MedicalCenter.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PatientService implements IPatientService {

    private final static Logger LOGGER = Logger.getLogger(PatientService.class.getName());

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsentRepository consentRepository;

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

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

    @Override
    public void deletePatient(long id) {
        patientRepository.findById(id).ifPresentOrElse(patient -> {
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
                    LOGGER.info("There is no consent to bind this patient with this research project");
                    throw new ConsentNotFoundException("Patient has not given consent");
                });
    }

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
