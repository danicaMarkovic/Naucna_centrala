package com.example.nCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nCentrala.model.ScienceArea;

public interface ScienceAreaRepository extends JpaRepository<ScienceArea, Long> {

	ScienceArea findByName(String name);
	
}
