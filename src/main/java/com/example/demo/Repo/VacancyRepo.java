package com.example.demo.Repo;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepo extends JpaRepository<Vacancy, Long> {
    Vacancy findById(long id);
    List<Vacancy> findByAuthor(User user);
}
