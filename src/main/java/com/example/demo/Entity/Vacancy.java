package com.example.demo.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @ManyToMany
    private Set<Feature> features = new HashSet<>();
    private double experience;
    private String description;
    private int salary;
    @ManyToOne
    private Company company;
    @ManyToOne
    private User author;
    @ManyToMany
    private List<User> candidates;
    public Vacancy() {
    }
    public Vacancy( User author, String name, String description, double experience, int salary) {
        this.author = author;
        this.name = name;
        this.description = description;
        this.experience = experience;
        this.salary = salary;
    }

    public String getFeatureForField()
    {
        String result = "";
        if (!this.getFeatures().isEmpty())
        {
            for (Feature feature: this.getFeatures())
            {
                result += feature.getName();
                result +=",";
            }
        }
        return result;
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

    public List<User> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<User> candidates) {
        this.candidates = candidates;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
