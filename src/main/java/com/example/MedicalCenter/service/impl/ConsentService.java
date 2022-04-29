package com.example.MedicalCenter.service.impl;

import com.example.MedicalCenter.exceptions.ConsentNotFoundException;
import com.example.MedicalCenter.exceptions.PatientNotFoundException;
import com.example.MedicalCenter.exceptions.ResearchProjectNotFoundException;
import com.example.MedicalCenter.model.Consent;
import com.example.MedicalCenter.model.ConsentImage;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.repo.ConsentImageRepository;
import com.example.MedicalCenter.repo.ConsentRepository;
import com.example.MedicalCenter.repo.PatientRepository;
import com.example.MedicalCenter.repo.ResearchProjectRepository;
import com.example.MedicalCenter.service.IConsentService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ConsentService implements IConsentService {

    private final static Logger LOGGER = Logger.getLogger(ConsentService.class.getName());

    @Autowired
    private ConsentRepository consentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    @Autowired
    private ConsentImageRepository consentImageRepository;

    @Override
    public void addConsent(long patientId, long researchProjectId){

        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("There is no patient with id: " + patientId));
        ResearchProject researchProject = researchProjectRepository.findById(researchProjectId).orElseThrow(() -> new ResearchProjectNotFoundException("There is no research project with id: " + researchProjectId));

            Consent consent = new Consent(researchProject, patient);
            consentRepository.save(consent);
            LOGGER.info("Consent has been added");
    }

    @Override
    public void withdrawConsent(long id){

        consentRepository.findById(id).ifPresentOrElse(consent -> {
            ResearchProject researchProject = consent.getResearchProject();
            Patient patient = consent.getPatient();
            researchProject.getPatients().remove(patient);
            consentRepository.delete(consent);
            LOGGER.info("Consent with id " + id + " has been removed. Patient is no longer part of the project");
        }, () -> {LOGGER.info("There's no consent with id " + id);
                throw new ConsentNotFoundException("There's no consent with id " + id);});
    }

    @Override
    public void uploadImage(long consentId, MultipartFile multipartFile, String fileName){
        consentRepository.findById(consentId).ifPresentOrElse(consent -> {
            ConsentImage image = new ConsentImage();
            image.setName(fileName);
            try {
                image.setContent(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setConsent(consent);
            consentImageRepository.save(image);
            consent.setConsentImage(image);
            consentRepository.save(consent);
        }, () -> {LOGGER.info("There's no consent with id " + consentId);
            throw new ConsentNotFoundException("There's no consent with id " + consentId);});
    }

    @Override
    public void deleteImage(long consentId){
        consentRepository.findById(consentId).ifPresentOrElse(consent -> {
            consentImageRepository.delete(consent.getConsentImage());
            LOGGER.info("Image has been deleted");
        }, () -> {LOGGER.info("There's no consent with id " + consentId);
            throw new ConsentNotFoundException("There's no consent with id " + consentId);});
    }

    @Override
    public Resource downloadImage(long consentId){
        Consent consent = consentRepository.findById(consentId).orElseThrow(() -> new ConsentNotFoundException("There is no consent with id: " + consentId));
        ConsentImage consentImage = consent.getConsentImage();
        byte[] image = consentImage.getContent();
        return new ByteArrayResource(image);
    }
}
