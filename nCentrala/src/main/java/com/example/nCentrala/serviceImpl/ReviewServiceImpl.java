package com.example.nCentrala.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Review;
import com.example.nCentrala.repository.ReviewRepository;
import com.example.nCentrala.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Review saveReview(Review review) {
		// TODO Auto-generated method stub
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllByArticle(Long articleId) {
		// TODO Auto-generated method stub
		return reviewRepository.findAllByArticleId(articleId);
	}

	@Override
	public void deleteReview(Review review) {
		// TODO Auto-generated method stub
		reviewRepository.delete(review);
	}

	@Override
	public List<Review> findAll() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll();
	}

}
