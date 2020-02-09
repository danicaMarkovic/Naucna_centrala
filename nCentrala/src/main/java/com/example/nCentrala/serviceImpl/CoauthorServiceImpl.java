package com.example.nCentrala.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Coauthor;
import com.example.nCentrala.repository.CoauthorRepository;
import com.example.nCentrala.service.CoauthorService;

@Service
public class CoauthorServiceImpl implements CoauthorService {
	
	@Autowired
	private CoauthorRepository coRepository;

	@Override
	public Coauthor getByEmail(String email) {
		// TODO Auto-generated method stub
		return coRepository.findOneByEmail(email);
	}

	@Override
	public Coauthor saveCoauthor(Coauthor c) {
		// TODO Auto-generated method stub
		return coRepository.save(c);
	}

	@Override
	public List<Coauthor> getAllByArticle(Long articleId) {
		// TODO Auto-generated method stub
		return coRepository.findAllByArticleId(articleId);
	}

}
