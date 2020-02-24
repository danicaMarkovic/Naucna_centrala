package com.example.nCentrala.modelElastic;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.ScienceArea;

@Document(indexName = AcceptedArticleIndex.INDEX_NAME, type = AcceptedArticleIndex.TYPE_NAME, shards = 1, replicas = 0)
public class AcceptedArticleIndex {

	public static final String INDEX_NAME = "articleindex";
	public static final String TYPE_NAME = "article";

	@Id
	private Long id;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String title;
	
	@Field(type=FieldType.Boolean, store = true)
	private boolean openAccess;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String author;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String keywords;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String text;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String scienceArea;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String apstract;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String journalName;
	
	@Field(type = FieldType.Long, store  = true)
	private Long journalId;
	
	@Field(type=FieldType.Text, store = true, analyzer = "serbian")
	private String coauthors;
	
	@Field(type=FieldType.Boolean, store = true)
	private boolean accepted;
	
	@Field(type=FieldType.Text, store = true)
	private String pdfPath;

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

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	@Override
	public String toString() {
		return "AcceptedArticleIndex [\n id=" + id + ",\n title=" + title + ",\n openAccess=" + openAccess + ",\n author="
				+ author + ",\n scienceArea=" + scienceArea + ",\n journalName=" + journalName + ",\n coauthors=" + coauthors
				+ "]";
	}

	
}
