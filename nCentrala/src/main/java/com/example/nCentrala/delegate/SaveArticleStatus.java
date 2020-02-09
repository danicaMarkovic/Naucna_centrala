package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.ArticleStatus;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.JournalService;
import com.example.nCentrala.service.ScienceAreaService;

@Service
public class SaveArticleStatus implements JavaDelegate {
	
	@Autowired
	private ScienceAreaService areaService;
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private ArticleService artService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String issn = (String) execution.getVariable("journals");
		String pdf = (String) execution.getVariable("pdf");
		boolean reject = (boolean) execution.getVariable("reject");
		Article article = artService.getLastInsertedArticle();
		article.setPdfPath(pdf);
		
		if(reject)
		{
			
			article.setAccepted(ArticleStatus.REJECTED);
			artService.saveArticle(article);
			
		}else
		{
			
			article.setAccepted(ArticleStatus.PROCESSING);
			artService.saveArticle(article);
		}
		
		
	}

}
