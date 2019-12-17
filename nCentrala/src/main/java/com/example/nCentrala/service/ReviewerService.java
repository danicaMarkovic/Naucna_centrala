package com.example.nCentrala.service;

import com.example.nCentrala.modelElastic.ReviewerIndex;

public interface ReviewerService {

	public Iterable<ReviewerIndex> findAll();
	public boolean addReviewer(ReviewerIndex article);
	public boolean updateReviewer(ReviewerIndex article);
	
}
