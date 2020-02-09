package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.service.ArticleService;

@Service
public class NewPdfDelegate implements JavaDelegate {
	
	@Autowired
	private ArticleService artService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String pdf = (String) execution.getVariable("newPdf");
		Article article = artService.getLastInsertedArticle();
		article.setPdfPath(pdf);
		artService.saveArticle(article);
		
		execution.setVariable("pdf", pdf);
		execution.setVariable("seePdf", pdf);
		
	}

}
