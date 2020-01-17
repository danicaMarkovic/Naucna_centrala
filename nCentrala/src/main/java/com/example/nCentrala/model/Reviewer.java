package com.example.nCentrala.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.example.nCentrala.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue("R")
public class Reviewer extends User {
	
	@Column
	private String title;
	
	@Column
	private boolean adminConfirmation;
	
	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Journal> journalReview = new HashSet<Journal>();

	public Reviewer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reviewer(Long id,String name, String surname, String email, String username, String password, String city,
			String state, boolean isActivated, Set<ScienceArea> areasOfInterest, Set<Role> roles, String title, boolean adminConfirmation ,Set<Journal> journalReview) {
		super( name, surname, email, username, password, city, state, isActivated, areasOfInterest, roles);
		// TODO Auto-generated constructor stub
		
		this.title = title;
		this.adminConfirmation = adminConfirmation;
		this.journalReview = journalReview;
		
	}

	public Reviewer(String name, String surname, String email, String username, String password, String city,
			String state, boolean isActivated, Set<ScienceArea> areasOfInterest, Set<Role> roles, String title, boolean adminConfirmation ,Set<Journal> journalReview) {
		super( name, surname, email, username, password, city, state, isActivated, areasOfInterest, roles);
		// TODO Auto-generated constructor stub
		
		this.title = title;
		this.adminConfirmation = adminConfirmation;
		this.journalReview = journalReview;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAdminConfirmation() {
		return adminConfirmation;
	}

	public void setAdminConfirmation(boolean adminConfirmation) {
		this.adminConfirmation = adminConfirmation;
	}

	public Set<Journal> getJournalReview() {
		return journalReview;
	}

	public void setJournalReview(Set<Journal> journalReview) {
		this.journalReview = journalReview;
	}
	
}
