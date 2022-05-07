package com.example.demo.Repo;

import com.example.demo.Entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepo extends JpaRepository<Feature, Long> {
    Feature findByName(String name);
}
