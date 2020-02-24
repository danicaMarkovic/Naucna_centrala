package com.example.nCentrala.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.PaymentMethod;
import com.example.nCentrala.model.Review;
import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.model.Role;
import com.example.nCentrala.model.RoleName;
import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.model.User;
import com.example.nCentrala.model.dto.ReviewDTO;
import com.example.nCentrala.model.dto.UserDTO;
import com.example.nCentrala.modelElastic.ArticleReviewIndex;
import com.example.nCentrala.modelElastic.ReviewerIndex;
import com.example.nCentrala.service.ArticleReviewIndexService;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.ReviewService;
import com.example.nCentrala.service.ReviewerIndexService;
import com.example.nCentrala.service.RoleService;
import com.example.nCentrala.service.ScienceAreaService;
import com.example.nCentrala.service.UserService;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:1337")
public class ReviewController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ScienceAreaService areaService;
	
	@Autowired
	private ArticleReviewIndexService indexService;
	
	@Autowired
	private UserService userService;

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
	
	@GetMapping("/getAll")
	public Iterable<ArticleReviewIndex> getAllArticles()
	{
		
		return indexService.findAllReviewes();
	}
}
