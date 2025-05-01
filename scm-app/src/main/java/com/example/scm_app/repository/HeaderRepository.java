package com.example.scm_app.repository;

import com.example.scm_app.model.Header;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeaderRepository extends JpaRepository<Header, String> {
    // Custom query methods can be defined here if needed
}
