package com.example.nCentrala.model.dto;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class SearchResultDTO {

	private Long id;
	
	private String title;
	
	private boolean openAccess;
	
	private String author;
	
	private String keywords;
	
	private String text;
	
	private String scienceArea;
	
	private String apstract;
	
	private String journalName;
	
	private Long journalId;
	
	private String coauthors;
	
	private boolean accepted;
	
	public SearchResultDTO() {}

	public SearchResultDTO(Long id, String title, boolean openAccess, String author, String keywords, String text,
			String scienceArea, String apstract, String journalName, Long journalId, String coauthors,
			boolean accepted) {
		super();
		this.id = id;
		this.title = title;
		this.openAccess = openAccess;
		this.author = author;
		this.keywords = keywords;
		this.text = text;
		this.scienceArea = scienceArea;
		this.apstract = apstract;
		this.journalName = journalName;
		this.journalId = journalId;
		this.coauthors = coauthors;
		this.accepted = accepted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isOpenAccess() {
		return openAccess;
	}

	public void setOpenAccess(boolean openAccess) {
		this.openAccess = openAccess;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getScienceArea() {
		return scienceArea;
	}

	public void setScienceArea(String scienceArea) {
		this.scienceArea = scienceArea;
	}

	public String getApstract() {
		return apstract;
	}

	public void setApstract(String apstract) {
		this.apstract = apstract;
	}

	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	public Long getJournalId() {
		return journalId;
	}

	public void setJournalId(Long journalId) {
		this.journalId = journalId;
	}

	public String getCoauthors() {
		return coauthors;
	}

	public void setCoauthors(String coauthors) {
		this.coauthors = coauthors;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
}
