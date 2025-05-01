package com.example.scm_app.repository;

import com.example.scm_app.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByPoNumber(String poNumber);
}