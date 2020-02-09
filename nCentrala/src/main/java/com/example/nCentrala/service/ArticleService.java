package com.example.nCentrala.service;

import com.example.nCentrala.model.Article;

public interface ArticleService {

	Article saveArticle(Article article);
	
	Article getByTitle(String title);
	
	Article getLastInsertedArticle();
	
}
