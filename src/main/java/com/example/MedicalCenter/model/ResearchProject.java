package com.example.MedicalCenter.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ResearchProject")
public class ResearchProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private LocalDate start;

    @OneToMany(mappedBy = "researchProject")
    @JsonIgnore
    private Set<Consent> consents;

    @ManyToMany
    @JoinTable(name="researchProject_patient",
    joinColumns = @JoinColumn(name = "researchProjectId"),
    inverseJoinColumns = @JoinColumn(name = "patientId"))
    @JsonIgnore
    private Set<Patient> patients;

    @OneToMany(mappedBy = "researchProject")
    private Set<LaboratoryTest> laboratoryTests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public Set<Consent> getConsents() {
        return consents;
    }

    public void setConsents(Set<Consent> consents) {
        this.consents = consents;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Set<LaboratoryTest> getLaboratoryTests() {
        return laboratoryTests;
    }

    public void setLaboratoryTests(Set<LaboratoryTest> laboratoryTests) {
        this.laboratoryTests = laboratoryTests;
    }
}
