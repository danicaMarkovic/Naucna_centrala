package com.example.nCentrala.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Coauthor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Article article;
	
	@Column(nullable = false)
	private double longit;
	
	@Column(nullable = false)
	private double latt;
	
	public Coauthor() {}

	public Coauthor(String email, String state, String city, String name, String surname, Article article) {
		super();
		this.email = email;
		this.state = state;
		this.city = city;
		this.name = name;
		this.surname = surname;
		this.article = article;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public double getLongit() {
		return longit;
	}

	public void setLongit(double longit) {
		this.longit = longit;
	}

	public double getLatt() {
		return latt;
	}

	public void setLatt(double latt) {
		this.latt = latt;
	}
	
}
