package com.dictionaryapp.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity{
    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageEnum languageName;
    @Column(nullable = false)
    private String description;
    @OneToMany(mappedBy = "language")
    private Set<Word> words;

    public LanguageEnum getLanguageName() {
        return languageName;
    }

    public void setLanguageName(LanguageEnum languageName) {
        this.languageName = languageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
