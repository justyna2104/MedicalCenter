package com.example.MedicalCenter.model;


import javax.persistence.*;

@Entity
@Table(name = "ConsentImage")
public class ConsentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] content;

    private String name;

    @OneToOne
    @JoinColumn(name = "ConsentId", referencedColumnName = "id")
    private Consent consent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Consent getConsent() {
        return consent;
    }

    public void setConsent(Consent consent) {
        this.consent = consent;
    }
}
