package com.example.MedicalCenter.service.impl;

import com.example.MedicalCenter.exceptions.PatientNotFoundException;
import com.example.MedicalCenter.exceptions.ResearchProjectNotFoundException;
import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.repo.LaboratoryTestRepository;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.repo.ResearchProjectRepository;
import com.example.MedicalCenter.service.ILaboratoryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class LaboratoryTestService implements ILaboratoryTestService {

    private final static Logger LOGGER = Logger.getLogger(LaboratoryTestService.class.getName());

    @Autowired
    private LaboratoryTestRepository laboratoryTestRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    @Override
    public void commissionLaboratoryTest(long patientId, long researchProjectId, LocalDateTime dateAndTime){
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("There is no patient with id: " + patientId));
        ResearchProject researchProject = researchProjectRepository.findById(researchProjectId).orElseThrow(() -> new ResearchProjectNotFoundException("There is no research project with id: " + researchProjectId));
        LaboratoryTest laboratoryTest = new LaboratoryTest(dateAndTime, patient, researchProject);
        laboratoryTestRepository.save(laboratoryTest);
        LOGGER.info("Laboratory test has been commissioned.");
    }
}
