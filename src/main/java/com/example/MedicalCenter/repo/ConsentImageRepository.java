package com.example.MedicalCenter.repo;


import com.example.MedicalCenter.model.ConsentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentImageRepository extends JpaRepository<ConsentImage, Long> {
    ConsentImage findByConsentId(long id);
}
