package com.example.nCentrala.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.repository.ArticleJPARepository;
import com.example.nCentrala.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleJPARepository articleRep;

	@Override
	public Article saveArticle(Article article) {
		// TODO Auto-generated method stub
		return articleRep.save(article);
	}

	@Override
	public Article getByTitle(String title) {
		// TODO Auto-generated method stub
		return articleRep.findOneByTitle(title);
	}

	@Override
	public Article getLastInsertedArticle() {
		// TODO Auto-generated method stub
		return articleRep.findTopByOrderByIdDesc();
	}

}
