package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ILaboratoryTestService {

    void orderLaboratoryTest(long patientId, long researchProjectId, LocalDateTime dateAndTime);

    void deleteLaboratoryTest(long id);
}
