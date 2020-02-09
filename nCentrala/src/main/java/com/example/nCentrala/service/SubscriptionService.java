package com.example.nCentrala.service;

import com.example.nCentrala.model.Subscription;

public interface SubscriptionService {

	Subscription findByUserAndJournal(Long uId, Long jId);
	
	Subscription saveSubscription(Subscription sub);
	
}
