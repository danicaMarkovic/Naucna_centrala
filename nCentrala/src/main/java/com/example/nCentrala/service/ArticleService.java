package com.example.nCentrala.service;

import java.util.List;

import com.example.nCentrala.model.Article;

public interface ArticleService {

	Article saveArticle(Article article);
	
	Article getByTitle(String title);
	
	Article getLastInsertedArticle();
	
	List<Article> getAll();
	
	Article getById(Long id);
	
}
