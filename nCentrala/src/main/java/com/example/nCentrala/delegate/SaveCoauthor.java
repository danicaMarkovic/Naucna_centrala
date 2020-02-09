package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Coauthor;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.CoauthorService;

@Service
public class SaveCoauthor implements JavaDelegate {
	
	@Autowired
	private CoauthorService coService;
	
	@Autowired
	private ArticleService articleService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String email = (String) execution.getVariable("coEmail");
		String state = (String) execution.getVariable("coState");
		String city = (String) execution.getVariable("coCity");
		
		Long numCo = (Long) execution.getVariable("coauthorsNum");
		Long smanji = numCo - 1;
		Article article = articleService.getLastInsertedArticle();
		
		Coauthor coauthor = new Coauthor(email, state, state, article);
		coService.saveCoauthor(coauthor);
		
		execution.setVariable("coauthorsNum", smanji);
		execution.setVariable("coEmail", "");
		execution.setVariable("coCity", "");
		execution.setVariable("coState", "");
	}

}
