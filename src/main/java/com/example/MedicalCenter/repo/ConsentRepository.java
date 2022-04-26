package com.example.MedicalCenter.repo;

import com.example.MedicalCenter.model.Consent;
import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {
    Consent findConsentByPatientAndResearchProject(Patient patient, ResearchProject researchProject);
}
