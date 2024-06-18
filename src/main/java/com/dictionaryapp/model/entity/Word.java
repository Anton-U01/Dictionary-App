package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "words")
public class Word extends BaseEntity{
    @Column(nullable = false)
    private String Term;
    @Column(nullable = false)
    private String translation;
    private String example;
    @Column(nullable = false)
    private Instant inputDate;
    @ManyToOne(optional = false)
    private Language language;
    @ManyToOne(optional = false)
    private User addedBy;

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Instant getInputDate() {
        return inputDate;
    }

    public void setInputDate(Instant inputDate) {
        this.inputDate = inputDate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}
