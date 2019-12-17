package com.example.nCentrala.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Journal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private boolean isOpenAccess;
	
	@Column(nullable = false)
	private ScienceArea scienceArea;
	
	@Column(nullable = false)
	private PayingMethod payingMethod;
	
	@Column
    @OneToMany(mappedBy="journal")
	private Set<Article> articles = new HashSet<Article>();
	
	public Journal() {}


	public Journal(Long id, String name, boolean isOpenAccess, ScienceArea scienceArea, PayingMethod payingMethod,
			Set<Article> articles) {
		super();
		this.id = id;
		this.name = name;
		this.isOpenAccess = isOpenAccess;
		this.scienceArea = scienceArea;
		this.payingMethod = payingMethod;
		this.articles = articles;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpenAccess() {
		return isOpenAccess;
	}

	public void setOpenAccess(boolean isOpenAccess) {
		this.isOpenAccess = isOpenAccess;
	}

	public ScienceArea getScienceArea() {
		return scienceArea;
	}

	public void setScienceArea(ScienceArea scienceArea) {
		this.scienceArea = scienceArea;
	}

	public PayingMethod getPayingMethod() {
		return payingMethod;
	}

	public void setPayingMethod(PayingMethod payingMethod) {
		this.payingMethod = payingMethod;
	}
	
	
}
