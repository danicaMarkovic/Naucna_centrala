package com.example.nCentrala;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Coauthor;
import com.example.nCentrala.model.Review;
import com.example.nCentrala.model.RoleName;
import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.model.User;
import com.example.nCentrala.modelElastic.RejectedArticleIndex;
import com.example.nCentrala.modelElastic.ReviewerIndex;
import com.example.nCentrala.service.ArticleIndexService;
import com.example.nCentrala.service.ArticleReviewIndexService;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.CoauthorService;
import com.example.nCentrala.service.RejectedArticleIndexService;
import com.example.nCentrala.service.ReviewService;
import com.example.nCentrala.service.ReviewerIndexService;
import com.example.nCentrala.service.UserService;

@Component
public class FilIndexesData {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleIndexService acceptedIndex;
	
	@Autowired
	private RejectedArticleIndexService rejectedIndex;
	
	@Autowired
	private ReviewerIndexService reviewerIndex;
	
	@Autowired
	private CoauthorService coauthorService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ArticleReviewIndexService articleReviewIndex;
	
	@PostConstruct
	private void init() throws Exception {
		
		this.addArticlesToIndex();
		this.addReviewersToIndex();
		this.addReviewesToIndex();
	}
	
	private void addArticlesToIndex() {
	
		List<Article> articles = articleService.getAll();
		
		for(Article article : articles)
		{
			List<Coauthor> coauthors = coauthorService.getAllByArticle(article.getId());
			String coA = "";
			for(Coauthor c : coauthors)
			{
				coA += c.getName() + " " + c.getSurname() + ",";
			}
			//odrstranjivanje poslednjeg zareza
			coA = coA.substring(0, coA.length() - 1);
			
			User user = userService.findUserByUsername("nikolina").get();
			
			String author = user.getName() + " " + user.getSurname();
			System.out.println("Status " + article.getAccepted().name());
			if(article.getAccepted().name().equals("ACCEPTED"))
			{
				acceptedIndex.addDataToIndex(article, coA, author);
			}else //odbijen
			{
				rejectedIndex.addRejectedArticleDataToIndex(article, coA, author);
			}
		}
	}
	
	private void addReviewersToIndex() {
		
		List<User> users = userService.getUserByRole(RoleName.ROLE_REVIEWER);
		
		for(User u : users) {
			
			ReviewerIndex revIndex = new ReviewerIndex();
			revIndex.setId(u.getId());
			revIndex.setCity(u.getCity());
			revIndex.setState(u.getState());
			revIndex.setName(u.getName());
			revIndex.setSurname(u.getSurname());
			revIndex.setRevLocation(new GeoPoint(u.getLat(), u.getLongit()));
			
			String areas = "";
			for(ScienceArea area : u.getAreasOfInterest())
			{
				areas += area.getName() + ",";
			}
			
			revIndex.setScienceAreas(areas.substring(0, areas.length() - 1));
			
			reviewerIndex.addReviewer(revIndex);
		}
	}
	
	public void addReviewesToIndex() {
		
		List<Review> reviewes = reviewService.findAll();
		
		for(Review r : reviewes) {
			
			articleReviewIndex.addToIndex(r);
		}
	}
}
