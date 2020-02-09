package com.example.nCentrala.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue("E")
public class Editor extends User {
	
	@Column
	private String title;
	
	@Column
	private boolean adminConfirmation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Journal journal;

	public Editor() {}

	public Editor(String name, String surname, String email, String username, String password, String city,
			String state, boolean isActivated, Set<ScienceArea> areasOfInterest, Set<Role> roles, String title, boolean adminConfirmation, Journal journal) {
		super(name, surname, email, username, password, city, state, isActivated, areasOfInterest, roles);
		// TODO Auto-generated constructor stub
		
		this.title = title;
		this.adminConfirmation = adminConfirmation;
		this.journal = journal;
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

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	
}
