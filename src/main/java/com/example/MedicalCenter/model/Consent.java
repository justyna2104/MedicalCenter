package com.example.MedicalCenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Consent")
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "researchProjectId", nullable = false)
    @JsonIgnore
    private ResearchProject researchProject;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    @JsonIgnore
    private Patient patient;

    public Consent(ResearchProject researchProject, Patient patient) {
        this.researchProject = researchProject;
        this.patient = patient;
    }

    public Consent() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResearchProject getResearchProject() {
        return researchProject;
    }

    public void setResearchProject(ResearchProject researchProject) {
        this.researchProject = researchProject;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
