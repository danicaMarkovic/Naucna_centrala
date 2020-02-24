package com.example.nCentrala.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.modelElastic.ReviewerIndex;
import com.example.nCentrala.repository.ReviewerIndexRepository;
import com.example.nCentrala.service.ReviewerIndexService;

@Service
public class ReviewerServiceImpl implements ReviewerIndexService {

	@Autowired
	public ReviewerIndexRepository reviewerRep;
	
	@Override
	public Iterable<ReviewerIndex> findAll() {
		// TODO Auto-generated method stub
		return reviewerRep.findAll();
	}

	@Override
	public boolean addReviewer(ReviewerIndex article) {
		// TODO Auto-generated method stub
		ReviewerIndex ret = reviewerRep.index(article);
		if(ret != null)
		{
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public Optional<ReviewerIndex> getById(Long id) {
		// TODO Auto-generated method stub
		return reviewerRep.findById(id);
	}

}
