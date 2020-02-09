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
public class SaveArticle implements JavaDelegate {
	
	@Autowired
	private ScienceAreaService areaService;
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private ArticleService artService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String title = (String) execution.getVariable("title");
		String keywords = (String) execution.getVariable("keywords");
		String apstract = (String) execution.getVariable("apstract");
		String scienceArea = (String) execution.getVariable("articleScienceArea");
		String issn = (String) execution.getVariable("journals");
		
		ScienceArea area = areaService.getByName(scienceArea);
		
		Journal journal = journalService.getJournalByIssn(issn);
		
		Article article = new Article(title, apstract, area, keywords, ArticleStatus.CREATED ,journal, null);
		artService.saveArticle(article);
	}

}
