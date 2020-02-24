package com.example.nCentrala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.service.RejectedArticleIndexService;
import com.example.nCentrala.modelElastic.RejectedArticleIndex;

@RestController
@RequestMapping("rejectedArticle")
@CrossOrigin(origins = "http://localhost:1337")
public class RejectedArticleController {

	@Autowired
	private RejectedArticleIndexService indexService;
	
	@GetMapping("/getAll")	
	public Iterable<RejectedArticleIndex> getAll(){
		
		return this.indexService.findAll();
	}
	
}
