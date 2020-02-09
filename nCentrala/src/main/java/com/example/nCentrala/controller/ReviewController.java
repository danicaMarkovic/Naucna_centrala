package com.example.nCentrala.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.PaymentMethod;
import com.example.nCentrala.model.Review;
import com.example.nCentrala.model.dto.ReviewDTO;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.ReviewService;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:1337")
public class ReviewController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value="articleReview",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReviewDTO>> getAllMethods(){

			Article article = articleService.getLastInsertedArticle();
			List<ReviewDTO> ret = new ArrayList<ReviewDTO>();
			
			for(Review r : reviewService.getAllByArticle(article.getId()))
			{
				ret.add(new ReviewDTO(r));
			}
		
			return new ResponseEntity<> (ret, HttpStatus.OK);
	}
}
