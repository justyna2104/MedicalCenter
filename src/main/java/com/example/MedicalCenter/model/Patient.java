package com.example.MedicalCenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personalDataId", referencedColumnName = "id")
    private PersonalData personalData;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<Consent> consents;

    @ManyToMany
    @JoinTable(name="researchProject_patient",
            joinColumns = @JoinColumn(name = "patientId"),
            inverseJoinColumns = @JoinColumn(name = "researchProjectId"))
    @JsonIgnore
    private Set<ResearchProject> researchProjects;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<LaboratoryTest> laboratoryTests;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<TestResult> testResults;

    public Patient(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Patient(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Set<Consent> getConsents() {
        return consents;
    }

    public void setConsents(Set<Consent> consents) {
        this.consents = consents;
    }

    public Set<ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    public void setResearchProjects(Set<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    public Set<LaboratoryTest> getLaboratoryTests() {
        return laboratoryTests;
    }

    public void setLaboratoryTests(Set<LaboratoryTest> laboratoryTests) {
        this.laboratoryTests = laboratoryTests;
    }

    public Set<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(Set<TestResult> testResults) {
        this.testResults = testResults;
    }
}
