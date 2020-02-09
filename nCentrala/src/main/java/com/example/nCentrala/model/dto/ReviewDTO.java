package com.example.nCentrala.model.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.nCentrala.model.Review;

public class ReviewDTO {

	private Long id;
	
	private String authorC;
	
	private String editorC;
	
	private String recommendation;
	
	private UserDTO user;

	public ReviewDTO(Long id, String authorC, String editorC, String recommendation, UserDTO user) {
		super();
		this.id = id;
		this.authorC = authorC;
		this.editorC = editorC;
		this.recommendation = recommendation;
		this.user = user;
	}
	
	public ReviewDTO(Review r) {
		
		this.id = r.getId();
		this.authorC = r.getAuthorC();
		this.editorC = r.getEditorC();
		this.recommendation = r.getRecommendation();
		this.user = new UserDTO(r.getUser());
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
}
