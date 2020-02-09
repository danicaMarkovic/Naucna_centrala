package com.example.nCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nCentrala.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	Subscription findByUserIdAndJournalId(Long uId, Long jI);
	
}
