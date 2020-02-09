package com.example.nCentrala.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Subscription;
import com.example.nCentrala.repository.SubscriptionRepository;
import com.example.nCentrala.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subRepository;

	@Override
	public Subscription findByUserAndJournal(Long uId, Long jId) {
		// TODO Auto-generated method stub
		return subRepository.findByUserIdAndJournalId(uId, jId);
		
	}

	@Override
	public Subscription saveSubscription(Subscription sub) {
		// TODO Auto-generated method stub
		return subRepository.save(sub);
	}
	
}
