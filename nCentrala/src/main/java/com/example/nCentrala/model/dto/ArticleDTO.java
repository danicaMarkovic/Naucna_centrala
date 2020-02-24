package com.example.nCentrala.model.dto;

import java.util.List;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.model.Coauthor;

public class ArticleDTO {

	private Long id;
	private String title;
	private String keywords;
	private String apstract;
	private String scienceArea;
	private String issn;
	private String pdfPath;
	private String authors;
	
	public ArticleDTO() {}
	
	public ArticleDTO(String title, String keywords, String apstract, String scienceArea, String issn, String pdfPath) {
		super();
		this.title = title;
		this.keywords = keywords;
		this.apstract = apstract;
		this.scienceArea = scienceArea;
		this.issn = issn;
		this.pdfPath = pdfPath;
	}
	
	public ArticleDTO(Article a, List<Coauthor> coauhtors) {
		this.id = a.getId();
		this.title = a.getTitle();
		this.keywords = a.getKeyWords();
		this.apstract = a.getApstract();
		this.scienceArea = a.getScienceArea().getName();
		this.issn = a.getJournal().getIssn();
		this.authors = a.getAuthor().getName() + "("+a.getAuthor().getCity()+"),";
		
		for(Coauthor c : coauhtors)
		{
			this.authors += " " + c.getName() + "(" + c.getCity() + "),";
		}
		this.authors.substring(0, this.authors.length() - 1);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getApstract() {
		return apstract;
	}

	public void setApstract(String apstract) {
		this.apstract = apstract;
	}

	public String getScienceArea() {
		return scienceArea;
	}

	public void setScienceArea(String scienceArea) {
		this.scienceArea = scienceArea;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
}
