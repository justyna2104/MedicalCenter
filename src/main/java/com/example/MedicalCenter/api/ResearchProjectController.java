package com.example.MedicalCenter.api;

import com.example.MedicalCenter.model.Patient;
import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.service.ResearchProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ResearchProjectController {


    @Autowired
    private ResearchProjectService researchProjectService;

    @PostMapping("/createResearchProject")
    public void createResearchProject(@RequestBody ResearchProject researchProject){
        researchProjectService.createResearchProject(researchProject);
    }

    @PutMapping("/updateResearchProject")
    public void updateResearchProject(@RequestBody ResearchProject researchProject){
        researchProjectService.updateResearchProject(researchProject);
    }

    @GetMapping("/getAllResearchProjects")
    public List<ResearchProject> getAllResearchProjects(){
        return researchProjectService.getAllResearchProjects();
    }

    @PostMapping("/deleteResearchProject")
    public void deleteResearchProject(@RequestParam(name="id") long id){
        researchProjectService.deleteResearchProject(id);
    }
}
