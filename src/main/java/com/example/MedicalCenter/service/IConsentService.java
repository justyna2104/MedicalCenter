package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.Consent;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IConsentService {

    void addConsent(long patientId, long researchProjectId);

    void withdrawConsent(long id);
}
