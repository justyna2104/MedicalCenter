package com.example.MedicalCenter.repo;

import com.example.MedicalCenter.model.ResearchProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchProjectRepository extends JpaRepository<ResearchProject, Long> {
}
