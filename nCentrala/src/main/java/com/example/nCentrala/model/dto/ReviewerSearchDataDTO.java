package com.example.nCentrala.model.dto;


public class ReviewerSearchDataDTO {

	private Long id;
	
	private String name;
	
	private String surname;
	
	private String city;
	
	private String state;
	
	private String scienceAreas;
	
	private String description;
	
	public ReviewerSearchDataDTO() {
		
	}
	
	public ReviewerSearchDataDTO(Long id, String name, String surname, String city, String state, String scienceAreas,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.state = state;
		this.scienceAreas = scienceAreas;
		this.description = description;
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

	public String getScienceAreas() {
		return scienceAreas;
	}

	public void setScienceAreas(String scienceAreas) {
		this.scienceAreas = scienceAreas;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
