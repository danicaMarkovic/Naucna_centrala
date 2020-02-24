package com.example.nCentrala.service;

import org.springframework.data.domain.Pageable;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.modelElastic.RejectedArticleIndex;


public interface RejectedArticleIndexService {

	public Iterable<RejectedArticleIndex> findAll();
	public boolean addArticle(RejectedArticleIndex article);
	public boolean addRejectedArticleDataToIndex(Article article, String coauthors, String author);
	
}
