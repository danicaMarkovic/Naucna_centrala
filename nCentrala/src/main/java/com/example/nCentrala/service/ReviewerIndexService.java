package com.example.nCentrala.service;

import java.util.Optional;

import com.example.nCentrala.modelElastic.ReviewerIndex;

public interface ReviewerIndexService {

	public Iterable<ReviewerIndex> findAll();
	public boolean addReviewer(ReviewerIndex article);
	public Optional<ReviewerIndex> getById(Long id);
}
