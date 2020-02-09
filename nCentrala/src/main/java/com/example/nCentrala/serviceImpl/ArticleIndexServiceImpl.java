package com.example.nCentrala.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.modelElastic.ArticleIndex;
import com.example.nCentrala.repository.ArticleIndexRepository;
import com.example.nCentrala.service.ArticleIndexService;

@Service
public class ArticleIndexServiceImpl implements ArticleIndexService {
	
	@Autowired
	private ArticleIndexRepository articleRep;

	@Override
	public Iterable<ArticleIndex> findAll() {
		// TODO Auto-generated method stub
		return articleRep.findAll();
	}

	@Override
	public boolean addArticle(ArticleIndex article) {
		// TODO Auto-generated method stub
		ArticleIndex ret = articleRep.index(article);
		if(ret != null)
		{
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public boolean updateArticle(ArticleIndex article) {
		// TODO Auto-generated method stub
		ArticleIndex ret  = articleRep.save(article);
		if(ret != null)
		{
			return true;
		}else
		{
			return false;
		}
	}

}
