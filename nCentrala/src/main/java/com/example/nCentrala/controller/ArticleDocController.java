package com.example.nCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.modelElastic.ArticleIndex;
import com.example.nCentrala.service.ArticleIndexService;


@RestController
@RequestMapping("article")
public class ArticleDocController {

	@Autowired
	private ArticleIndexService articleService;
	
	@GetMapping("/getAll")
	public Iterable<ArticleIndex> getAllArticles()
	{
		
		return articleService.findAll();
	}
	
	@PostMapping("/addArticle")
	public ResponseEntity<String> addNewArticle(@RequestBody ArticleIndex article)
	{
		boolean ret = articleService.addArticle(article);
		
		if(ret)
		{
			return new ResponseEntity<String>("Article added", HttpStatus.OK);
		}else
		{
			return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
		}
	}
}
