package com.example.MedicalCenter.api;

import com.example.MedicalCenter.model.ResearchProject;
import com.example.MedicalCenter.service.IResearchProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ResearchProjectController {


    @Autowired
    private IResearchProjectService researchProjectService;

    @PostMapping("/createResearchProject")
    public void createResearchProject(@RequestBody ResearchProject researchProject){
        researchProjectService.createResearchProject(researchProject);
    }

    @PostMapping("/updateResearchProjectDate")
    public void updateDateOfStart(@RequestParam(name = "id") long id, @RequestBody LocalDate dateOfStart){
        researchProjectService.updateDateOfStart(id, dateOfStart);
    }

    @PostMapping("/updateResearchProjectDescription")
    public void updateDescription(@RequestParam(name = "id") long id, @RequestBody String description){
        researchProjectService.updateDescription(id, description);
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
