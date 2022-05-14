package com.example.demo.Repo;

import com.example.demo.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
    Profile findById(long id);

    Profile findByEmail(String email);
}
