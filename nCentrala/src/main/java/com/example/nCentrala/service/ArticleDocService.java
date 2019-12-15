package com.example.nCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.modelElastic.ArticleDoc;
import com.example.nCentrala.repository.ArticleDocRepository;

@Service
public class ArticleDocService {

	@Autowired
	private ArticleDocRepository articleRep;
	
	public ArticleDocService() {}
	
	public Iterable<ArticleDoc> findAll() {
		
		return articleRep.findAll();
	}
	
	public boolean addArticle(ArticleDoc article)
	{
		ArticleDoc ret = articleRep.index(article);
		if(ret != null)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean updateArticle(ArticleDoc article) {
		
		ArticleDoc ret  = articleRep.save(article);
		if(ret != null)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	
}
