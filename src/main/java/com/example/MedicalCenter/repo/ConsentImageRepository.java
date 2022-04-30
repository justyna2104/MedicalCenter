package com.example.MedicalCenter.repo;


import com.example.MedicalCenter.model.ConsentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsentImageRepository extends JpaRepository<ConsentImage, Long> {
    Optional<ConsentImage> findByConsentId(long id);
}
