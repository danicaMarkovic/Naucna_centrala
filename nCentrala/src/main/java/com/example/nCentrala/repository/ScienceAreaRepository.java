package com.example.nCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nCentrala.model.ScienceArea;

public interface ScienceAreaRepository extends JpaRepository<ScienceArea, Long> {

	ScienceArea findByName(String name);
}
