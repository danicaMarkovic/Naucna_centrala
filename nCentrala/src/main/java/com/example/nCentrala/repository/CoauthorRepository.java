package com.example.nCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nCentrala.model.Coauthor;

public interface CoauthorRepository extends JpaRepository<Coauthor, Long> {

	Coauthor findOneByEmail(String email);
	
	List<Coauthor> findAllByArticleId(Long id);
}
