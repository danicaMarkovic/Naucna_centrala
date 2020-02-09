package com.example.nCentrala.service;

import java.util.List;

import com.example.nCentrala.model.Review;

public interface ReviewService {

	Review saveReview(Review review);
	
	List<Review> getAllByArticle(Long articleId);
	
	void deleteReview(Review review);
	
}
