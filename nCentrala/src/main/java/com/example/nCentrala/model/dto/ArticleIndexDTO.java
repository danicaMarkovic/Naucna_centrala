package com.example.nCentrala.model.dto;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.web.multipart.MultipartFile;

public class ArticleIndexDTO {

	private Long id;
	
	private String journalName;
	
	private String title;
	
	private String author;
	
	private String keywords;
	
	private String fileName;
	
	private String scienceArea;
	
	public ArticleIndexDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getScienceArea() {
		return scienceArea;
	}

	public void setScienceArea(String scienceArea) {
		this.scienceArea = scienceArea;
	}
	
}
