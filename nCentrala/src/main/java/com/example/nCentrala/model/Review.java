package com.example.nCentrala.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String authorC;
	
	@Column(nullable = false)
	private String editorC;
	
	@Column(nullable = false)
	private String recommendation;
	
	@OneToOne
	private Article article;
	
	@OneToOne
	private User user;
	
	public Review() {}

	public Review(String authorC, String editorC, String recommendation, Article article, User user) {
		super();
		this.authorC = authorC;
		this.editorC = editorC;
		this.recommendation = recommendation;
		this.article = article;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorC() {
		return authorC;
	}

	public void setAuthorC(String authorC) {
		this.authorC = authorC;
	}

	public String getEditorC() {
		return editorC;
	}

	public void setEditorC(String editorC) {
		this.editorC = editorC;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
