package com.example.demo.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Company company;
    @ManyToOne
    private User author;
    private String name;
    private String description;
    @ManyToMany
    private Set<Feature> features;
    public Vacancy() {
    }

    public Vacancy(Company company, User author, String name, String description, Set<Feature> features) {
        this.company = company;
        this.author = author;
        this.name = name;
        this.description = description;
        this.features = features;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
