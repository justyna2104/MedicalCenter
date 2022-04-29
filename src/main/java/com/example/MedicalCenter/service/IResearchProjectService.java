package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.ResearchProject;

import java.time.LocalDate;
import java.util.List;

public interface IResearchProjectService {

    void createResearchProject(ResearchProject researchProject);

    void updateDateOfStart(long id, LocalDate date);

    void updateDescription(long id, String description);

    List<ResearchProject> getAllResearchProjects();

    void deleteResearchProject(long id);
}
