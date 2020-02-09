package com.example.nCentrala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nCentrala.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findAllByArticleId(Long id);
	
}
