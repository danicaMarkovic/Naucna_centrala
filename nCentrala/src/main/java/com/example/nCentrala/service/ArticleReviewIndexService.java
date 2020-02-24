package com.example.nCentrala.service;

import com.example.nCentrala.model.Review;
import com.example.nCentrala.modelElastic.ArticleReviewIndex;

public interface ArticleReviewIndexService {

	public Iterable<ArticleReviewIndex> findAllReviewes();
	public boolean addToIndex(Review review);
}
