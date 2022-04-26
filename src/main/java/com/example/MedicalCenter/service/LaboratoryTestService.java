package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.repo.LaboratoryTestRepository;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.repo.ResearchProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class LaboratoryTestService {

    private final static Logger LOGGER = Logger.getLogger(LaboratoryTestService.class.getName());

    @Autowired
    private LaboratoryTestRepository laboratoryTestRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    public void commissionLaboratoryTest(long patientId, long researchProjectId, LaboratoryTest laboratoryTestInfo){
        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<ResearchProject> researchProject = researchProjectRepository.findById(researchProjectId);
        LaboratoryTest laboratoryTest = new LaboratoryTest(laboratoryTestInfo.getDateAndTime(), patient.get(), researchProject.get());
        laboratoryTestRepository.save(laboratoryTest);
        LOGGER.info("Laboratory test has been commissioned.");
    }
}
