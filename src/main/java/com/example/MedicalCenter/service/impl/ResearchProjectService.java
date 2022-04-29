package com.example.MedicalCenter.service.impl;

import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.PersonalData;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.repo.ResearchProjectRepository;
import com.example.MedicalCenter.service.IResearchProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ResearchProjectService implements IResearchProjectService {

    private final static Logger LOGGER = Logger.getLogger(ResearchProject.class.getName());

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    @Override
    public void createResearchProject(ResearchProject researchProject){
        researchProjectRepository.save(researchProject);
    }

    @Override
    public void updateDateOfStart(long id, LocalDate date){
        researchProjectRepository.findById(id).ifPresentOrElse(researchProject -> {
            researchProject.setStart(date);
            researchProjectRepository.save(researchProject);
        }, () -> LOGGER.info("There's no research project with id " + id));
    }

    @Override
    public void updateDescription(long id, String description){
        researchProjectRepository.findById(id).ifPresentOrElse(researchProject -> {
            researchProject.setDescription(description);
            researchProjectRepository.save(researchProject);
        }, () -> LOGGER.info("There's no research project with id " + id));
    }


    @Override
    public List<ResearchProject> getAllResearchProjects(){
        return researchProjectRepository.findAll();
    }

    @Override
    public void deleteResearchProject(long id){
        researchProjectRepository.findById(id).ifPresentOrElse(researchProject -> {
            researchProjectRepository.delete(researchProject);
        }, () -> LOGGER.info("There's no research project with id " + id));
    }
}
