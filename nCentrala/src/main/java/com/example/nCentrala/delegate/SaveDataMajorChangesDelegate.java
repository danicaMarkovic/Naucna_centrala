package com.example.nCentrala.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Review;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.ReviewService;

@Service
public class SaveDataMajorChangesDelegate implements JavaDelegate {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ArticleService articleService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String newPdf = (String) execution.getVariable("changedPdf");

		execution.setVariable("authorsPdf", newPdf);
		
		Article article = articleService.getLastInsertedArticle();
		
		//brisanje prethodnih revizija, nisu vise validne jer su recenzirale stari pdf, 
		List<Review> reviews = reviewService.getAllByArticle(article.getId());
		
		for(Review r : reviews)
		{
			reviewService.deleteReview(r);
		}
	}

}
