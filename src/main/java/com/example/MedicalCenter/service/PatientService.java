package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.Consent;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.PersonalData;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.repo.ConsentRepository;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.repo.ResearchProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PatientService {

    private final static Logger LOGGER = Logger.getLogger(PatientService.class.getName());

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ConsentRepository consentRepository;

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    public void registerPatient(PersonalData personalData){
        Patient patient = new Patient(personalData);
        patientRepository.save(patient);
    }

    public void updatePatient(Patient patient){
        patientRepository.save(patient);
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public void deletePatient(long id){
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent()){
            patientRepository.delete(patient.get());
            LOGGER.info("Patient with id " + id + " has been removed");
        }else LOGGER.info("There's no patient with id " + id);
    }

    public void bindPatientWithResearchProject(long patientId, long researchProjectId){
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<ResearchProject> researchProject = researchProjectRepository.findById(researchProjectId);
        Consent consent = consentRepository.findConsentByPatientAndResearchProject(patient.get(), researchProject.get());

        if (consent != null){
            researchProject.get().getPatients().add(patient.get());
            researchProjectRepository.save(researchProject.get());
            LOGGER.info("Patient has been binded with research project");
        }else LOGGER.info("There is no consent to bind this patient with this research project");
    }

    public void unbindPatientWithResearchProject(long patientId, long researchProjectId){
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<ResearchProject> researchProject = researchProjectRepository.findById(researchProjectId);
        researchProject.get().getPatients().remove(patient);
        patient.get().getResearchProjects().remove(researchProject);
        researchProjectRepository.save(researchProject.get());
        patientRepository.save(patient.get());
        LOGGER.info("The patient has been removed form research project");
    }
}
