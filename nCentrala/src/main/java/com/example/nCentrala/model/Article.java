package com.example.nCentrala.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String apstract;
	
	@ManyToOne
	private ScienceArea scienceArea;
	
	@Column(nullable = false)
	private String keyWords;
	
	@Column(nullable = false)
	private ArticleStatus accepted;
	
	@ManyToOne
	private Journal journal;
	
	
	private String pdfPath;
	
	public Article() {}

	public Article(String title, String apstract, ScienceArea scienceArea, String keyWords, ArticleStatus accepted,
			Journal journal, String pdfPath) {
		super();
		this.title = title;
		this.apstract = apstract;
		this.scienceArea = scienceArea;
		this.keyWords = keyWords;
		this.accepted = accepted;
		this.journal = journal;
		this.pdfPath = pdfPath;
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

	public String getApstract() {
		return apstract;
	}

	public void setApstract(String apstract) {
		this.apstract = apstract;
	}

	public ScienceArea getScienceArea() {
		return scienceArea;
	}

	public void setScienceArea(ScienceArea scienceArea) {
		this.scienceArea = scienceArea;
	}

	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}


	public String getKeyWords() {
		return keyWords;
	}


	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public ArticleStatus getAccepted() {
		return accepted;
	}

	public void setAccepted(ArticleStatus accepted) {
		this.accepted = accepted;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
}
