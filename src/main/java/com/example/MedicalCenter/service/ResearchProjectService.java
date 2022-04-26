package com.example.MedicalCenter.service;

import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.PersonalData;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.repo.ResearchProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ResearchProjectService {

    private final static Logger LOGGER = Logger.getLogger(ResearchProject.class.getName());

    @Autowired
    private ResearchProjectRepository researchProjectRepository;

    public void createResearchProject(ResearchProject researchProject){
        researchProjectRepository.save(researchProject);
    }

    public void updateResearchProject(ResearchProject researchProject){
        researchProjectRepository.save(researchProject);
    }

    public List<ResearchProject> getAllResearchProjects(){
        return researchProjectRepository.findAll();
    }

    public void deleteResearchProject(long id){
        Optional<ResearchProject> researchProject = researchProjectRepository.findById(id);
        if(researchProject.isPresent()){
            researchProjectRepository.delete(researchProject.get());
            LOGGER.info("Research project with id " + id + " has been removed");
        }else LOGGER.info("There's no research project with id " + id);
    }
}
