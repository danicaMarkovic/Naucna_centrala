package com.example.nCentrala.service;

import java.util.List;

import com.example.nCentrala.model.Coauthor;

public interface CoauthorService {

	Coauthor getByEmail(String email);
	
	Coauthor saveCoauthor(Coauthor c);
	
	List<Coauthor> getAllByArticle(Long articleId);
}
