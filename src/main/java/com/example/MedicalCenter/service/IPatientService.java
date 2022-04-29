package com.example.MedicalCenter.service;

import com.example.MedicalCenter.exceptions.PatientNotFoundException;
import com.example.MedicalCenter.model.Consent;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.PersonalData;
import com.example.MedicalCenter.model.ResearchProject;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    void registerPatient(PersonalData personalData);

    //void updatePatient(long id, PersonalData personalData);

    void updatePatientPersonalData(long id, PersonalData personalData);

    PersonalData getPatientsPersonalData(long id);

    List<Patient> getAllPatients();

    void deletePatient(long id);

    void bindPatientWithResearchProject(long patientId, long researchProjectId);

    void unbindPatientWithResearchProject(long patientId, long researchProjectId);
}
