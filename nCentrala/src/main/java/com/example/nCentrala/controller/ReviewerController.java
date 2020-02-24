package com.example.nCentrala.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.modelElastic.ReviewerIndex;
import com.example.nCentrala.service.ReviewerIndexService;
import com.example.nCentrala.model.dto.ReviewerSearchDataDTO;

@RestController
@RequestMapping("reviewer")
@CrossOrigin(origins = "http://localhost:1337")
public class ReviewerController {

	@Autowired
	private ReviewerIndexService reviewerService;
	
	@GetMapping("/getAll")
	public Iterable<ReviewerIndex> getAllArticles()
	{
		
		return reviewerService.findAll();
	}
	
	@GetMapping("/getAllReviewers")
	public ResponseEntity<List<ReviewerSearchDataDTO>> getRev(){
		
		Iterable<ReviewerIndex> indexes = reviewerService.findAll();
		List<ReviewerSearchDataDTO> ret = new ArrayList<ReviewerSearchDataDTO>();
		
		for(ReviewerIndex index : indexes)
		{
			ret.add(new ReviewerSearchDataDTO(index.getId(), index.getName(), index.getSurname(), index.getCity(), index.getState(), index.getScienceAreas(), ""));
		}
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
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
