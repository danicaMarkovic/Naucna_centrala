package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.service.ArticleService;

@Service
public class SaveChangesByAuthorDelegate implements JavaDelegate {
	
	@Autowired
	private ArticleService articleService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String newPdf = (String) execution.getVariable("changedPdf");
		Article article = articleService.getLastInsertedArticle();
		article.setPdfPath(newPdf);
		
	}

}
