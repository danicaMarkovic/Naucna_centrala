package com.example.nCentrala.model.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.nCentrala.model.ScienceArea;

public class UserDTO {

	
	private Long id;
	
	private String name;
	
	private String surname;

	private String email;
	
	private String password;
	
	private String city;
	
	private String state;
	
	private String password2;
	
	private boolean reviewer;
	
	private String username;
	
	private Set<ScienceArea> areas = new HashSet<>();
	
	public UserDTO() {}
	

	public UserDTO(Long id, String name, String surname, String email, String password, String city, String state,
			String password2, boolean reviewer, String username, Set<ScienceArea> areas) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.city = city;
		this.state = state;
		this.password2 = password2;
		this.reviewer = reviewer;
		this.username = username;
		this.areas = areas;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public boolean isReviewer() {
		return reviewer;
	}

	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<ScienceArea> getAreas() {
		return areas;
	}

	public void setAreas(Set<ScienceArea> areas) {
		this.areas = areas;
	}
	
	
}
