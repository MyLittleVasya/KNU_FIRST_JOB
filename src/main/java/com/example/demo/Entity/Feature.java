package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "features")
public class Feature {

    @ManyToOne
    @JoinColumn(name="feature_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "feature", nullable = false)
    private Vacancy vacancy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public Feature(String name) {
        this.name = name;
    }

    public Feature() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
