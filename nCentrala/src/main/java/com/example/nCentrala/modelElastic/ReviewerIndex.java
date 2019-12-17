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
	@Field(type=FieldType.Text, store = true)
	private Long id;
	
	@Field(type=FieldType.Text, store = true)
	private String name;
	
	@Field(type=FieldType.Text, store = true)
	private String surname;
	
	@Field(type=FieldType.Text, store = true)
	private String city;
	
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

	

	public GeoPoint getRevLocation() {
		return revLocation;
	}

	public void setRevLocation(GeoPoint revLocation) {
		this.revLocation = revLocation;
	}

	public static String getIndexName() {
		return INDEX_NAME;
	}

	public static String getTypeName() {
		return TYPE_NAME;
	}
	
}
