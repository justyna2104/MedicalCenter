package com.example.MedicalCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "TestResult")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    @JsonIgnore
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "LaboratoryTestId", referencedColumnName = "id")
    private LaboratoryTest laboratoryTest;

    public TestResult(String description, Patient patient, LaboratoryTest laboratoryTest) {
        this.description = description;
        this.patient = patient;
        this.laboratoryTest = laboratoryTest;
    }

    public TestResult() {}

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LaboratoryTest getLaboratoryTest() {
        return laboratoryTest;
    }

    public void setLaboratoryTest(LaboratoryTest laboratoryTest) {
        this.laboratoryTest = laboratoryTest;
    }
}
