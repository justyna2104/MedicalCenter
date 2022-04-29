package com.example.MedicalCenter.repo;

import com.example.MedicalCenter.model.LaboratoryTest;
import com.example.MedicalCenter.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryTestRepository extends JpaRepository<LaboratoryTest, Long> {
    LaboratoryTest findByTestResult(TestResult testResult);
}
