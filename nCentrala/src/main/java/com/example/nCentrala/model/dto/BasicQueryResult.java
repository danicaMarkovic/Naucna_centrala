package com.example.nCentrala.model.dto;

import com.example.nCentrala.modelElastic.AcceptedArticleIndex;

public class BasicQueryResult {

	private String title;
	
	private String authors;
	
	private String keywords;
	
	private String highlight;
	
	private boolean openAccess;
	
	private String pdfPath;
	
	public BasicQueryResult() {
		// TODO Auto-generated constructor stub
	}
	
	public BasicQueryResult(String title, String authors, String keywords, String highlight, boolean openAccess, String pdfPath) {
		super();
		this.title = title;
		this.authors = authors;
		this.keywords = keywords;
		this.highlight = highlight;
		this.openAccess = openAccess;
		this.pdfPath = pdfPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public boolean isOpenAccess() {
		return openAccess;
	}

	public void setOpenAccess(boolean openAccess) {
		this.openAccess = openAccess;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

}
