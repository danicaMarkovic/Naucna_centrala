package com.example.nCentrala.service;

import org.springframework.stereotype.Service;

import com.example.nCentrala.modelElastic.ArticleIndex;

public interface ArticleService {

	public Iterable<ArticleIndex> findAll();
	public boolean addArticle(ArticleIndex article);
	public boolean updateArticle(ArticleIndex article);
	
}
