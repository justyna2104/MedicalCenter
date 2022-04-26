package com.example.MedicalCenter.repo;

import com.example.MedicalCenter.model.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
}
