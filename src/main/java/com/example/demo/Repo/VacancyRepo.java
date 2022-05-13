package com.example.demo.Repo;

import com.example.demo.Entity.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepo extends JpaRepository<Vacancy, Long> {
    Vacancy findById(long id);
    Page<Vacancy> findAll(Pageable pageable);
}
