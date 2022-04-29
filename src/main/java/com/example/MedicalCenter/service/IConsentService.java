package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.Consent;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IConsentService {

    void addConsent(long patientId, long researchProjectId);

    void withdrawConsent(long id);

    void uploadImage(long consentId, MultipartFile multipartFile, String fileName);

    void deleteImage(long consentId);

    Resource downloadImage(long consentId);
}
