package com.example.nCentrala.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.ArticleStatus;
import com.example.nCentrala.model.Coauthor;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.model.dto.ArticleDTO;
import com.example.nCentrala.model.dto.ArticleIndexDTO;
import com.example.nCentrala.modelElastic.AcceptedArticleIndex;
import com.example.nCentrala.service.ArticleIndexService;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.CoauthorService;
import com.example.nCentrala.service.JournalService;
import com.example.nCentrala.service.ScienceAreaService;


@RestController
@RequestMapping("article")
@CrossOrigin(origins = "http://localhost:1337")
public class ArticleDocController {
	
	private static PageRequest page;

	@Autowired
	private ArticleIndexService articleService;
	
	@Autowired
	private ScienceAreaService areaService;
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private ArticleService artService;
	
	@Autowired
	private CoauthorService coService;
	
	@GetMapping("/getAll")
	public Iterable<AcceptedArticleIndex> getAllArticles()
	{
		//Pageable pageable = page.of(pageNum, 2);
		return articleService.findAll();
	}
	
	@PostMapping("/addArticle")
	public ResponseEntity<String> addNewArticle(@RequestBody AcceptedArticleIndex article)
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
	
	@GetMapping(value="getArticles")
	public ResponseEntity<List<ArticleDTO>> getArticles(){
		
		List<Article> articles = artService.getAll();
		
		List<ArticleDTO> dtos = new ArrayList<ArticleDTO>();
		
		for(Article a : articles)
		{
			if(a.getAccepted().name().equals("ACCEPTED"))
			{
				dtos.add(new ArticleDTO(a, coService.getAllByArticle(a.getId())));
			}
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
}
