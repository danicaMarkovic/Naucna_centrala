package com.example.nCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nCentrala.model.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

	Journal findTopByOrderByIdDesc();
	
	Journal findByIssn(String issn);
	
	Journal findByName(String name);

	List<Journal> findAllByIsActivated(boolean activated);
	
}
