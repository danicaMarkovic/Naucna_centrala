package com.example.nCentrala.model.dto;

import com.example.nCentrala.model.Subscription;
import com.example.nCentrala.model.SubscriptionType;

public class SubscriptionDTO {

	private Long id;
	
	private double price;
	
	private String userEmail;
	
	private String  journalIssn;
	
	public SubscriptionDTO() {}

	public SubscriptionDTO(Long id, double price, String userEmail, String journalIssn) {
		super();
		this.id = id;
		this.price = price;
		this.userEmail = userEmail;
		this.journalIssn = journalIssn;
	}
	
	public SubscriptionDTO(Subscription s) {
		super();
		this.id = s.getId();
		this.price = s.getPrice();
		this.userEmail = s.getUser().getEmail();
		this.journalIssn = s.getJournal().getIssn();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getJournalIssn() {
		return journalIssn;
	}

	public void setJournalIssn(String journalIssn) {
		this.journalIssn = journalIssn;
	}
	
}
