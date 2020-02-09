package com.example.nCentrala.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.ReviewerService;
import com.example.nCentrala.service.UserService;

@Service
public class CheckReviewersExistenceDelegate implements JavaDelegate {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		Article article = articleService.getLastInsertedArticle();
		
		List<Reviewer> reviewers = userService.getJournalReviewers(article.getJournal().getId());
		
		if(reviewers.size() >= 2)
		{
			execution.setVariable("hasReviewers", true);
			
		}else
		{
			execution.setVariable("hasReviewers", false);
		}
	}

}
