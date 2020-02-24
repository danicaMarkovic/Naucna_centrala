package com.example.nCentrala.modelElastic;

import javax.persistence.Id;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

@Document(indexName = ReviewerIndex.INDEX_NAME, type = ReviewerIndex.TYPE_NAME, shards = 1, replicas = 0)
public class ReviewerIndex {
	
	public static final String INDEX_NAME = "reviewerindex";
	public static final String TYPE_NAME = "reviewer";
	
	@Id
	private Long id;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String name;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String surname;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String city;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String state;

	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String scienceAreas;
	
	@GeoPointField
	private GeoPoint revLocation;

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

	public GeoPoint getRevLocation() {
		return revLocation;
	}

	public void setRevLocation(GeoPoint revLocation) {
		this.revLocation = revLocation;
	}

	@Override
	public String toString() {
		return "ReviewerIndex [id=" + id + ", name=" + name + ", surname=" + surname + ", city=" + city + ", state="
				+ state + ", scienceAreas=" + scienceAreas + ", revLocation=" + revLocation + "]";
	}

}
