package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Review;
import com.example.nCentrala.model.User;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.ReviewService;
import com.example.nCentrala.service.UserService;

@Service
public class SaveReviewDelegate implements JavaDelegate {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String commentForAuthor = (String) execution.getVariable("authorComment");
		String commentForEditor = (String) execution.getVariable("editorComment");
		String recommendation = (String) execution.getVariable("rewRecommendation");
		System.out.println("Rec: " + recommendation);
		
		String username = (String) execution.getVariable("oneReviewer");
		
		User user = userService.findUserByUsername(username).get();
		
		Article article = articleService.getLastInsertedArticle();
		
		Review review = new Review(commentForAuthor, commentForEditor, recommendation, article, user);
		reviewService.saveReview(review);
		
		execution.setVariable("authorComment", "");
		execution.setVariable("editorComment", "");
	}

}
