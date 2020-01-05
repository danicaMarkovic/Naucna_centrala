package com.example.nCentrala.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.modelElastic.ReviewerIndex;
import com.example.nCentrala.repository.ReviewerRepository;
import com.example.nCentrala.service.ReviewerService;

@Service
public class ReviewerServiceImpl implements ReviewerService {

	@Autowired
	public ReviewerRepository reviewerRep;
	
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

}
