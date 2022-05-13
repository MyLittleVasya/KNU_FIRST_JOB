package com.example.demo.Repo;

import com.example.demo.Entity.Feature;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepo extends JpaRepository<Feature, Long> {
    Feature findByName(String name);
}
