package com.example.nCentrala.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Review;
import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.ReviewService;
import com.example.nCentrala.service.UserService;

@Service
public class OneMoreReviewAvailabilityDelegate implements JavaDelegate {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Usaooooo");
		execution.setVariable("available", true);
		
		Article article = articleService.getLastInsertedArticle();
		
		List<Reviewer> journalReviewers = userService.getJournalReviewers(article.getJournal().getId());
//		
		List<Review> reviews = reviewService.getAllByArticle(article.getId());
		
		if(journalReviewers.size() > reviews.size()) //u listi recenzenata postoji onaj koji nije recenzirao clanak
		{
			execution.setVariable("available", true);
			System.out.println("Ima jos koji ");
		}else
		{
			execution.setVariable("available", false);
			System.out.println("Nema viseeee ");
		}
	}

}
