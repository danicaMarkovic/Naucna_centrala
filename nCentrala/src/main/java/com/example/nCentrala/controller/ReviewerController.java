package com.example.nCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.modelElastic.ReviewerIndex;
import com.example.nCentrala.service.ReviewerService;

@RestController
@RequestMapping("reviewer")
public class ReviewerController {

	@Autowired
	private ReviewerService reviewerService;
	
	@GetMapping("/getAll")
	public Iterable<ReviewerIndex> getAllArticles()
	{
		
		return reviewerService.findAll();
	}
	
	@PostMapping("/addReviewer")
	public ResponseEntity<String> addNewArticle(@RequestBody ReviewerIndex article)
	{
		boolean ret = reviewerService.addReviewer(article);
		
		if(ret)
		{
			return new ResponseEntity<String>("Reviewer added", HttpStatus.OK);
		}else
		{
			return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
