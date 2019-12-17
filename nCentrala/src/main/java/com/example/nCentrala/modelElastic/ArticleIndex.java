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

@Document(indexName = ArticleIndex.INDEX_NAME, type = ArticleIndex.TYPE_NAME, shards = 1, replicas = 0)
public class ArticleIndex {

	public static final String INDEX_NAME = "articleindex";
	public static final String TYPE_NAME = "article";
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	@Id
	@Field(type=FieldType.Text, store = true)
	private Long id;
	
	@Field(type=FieldType.Text, store = true)
	private String title;

	@Column(nullable = false)
	private String apstract;
	
	@Column(nullable = false)
	private String content;
	
	/*@Field(type=FieldType.Text, store = true)
	private String keyWords;
	
	@Field(type=FieldType.Text, store = true)
	private String scienceArea; */
	
	@Field(type=FieldType.Text, store = true)
	private String journal;

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
	

	/*public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getScienceArea() {
		return scienceArea;
	}

	public void setScienceArea(String scienceArea) {
		this.scienceArea = scienceArea;
	}*/

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public static String getIndexName() {
		return INDEX_NAME;
	}

	public static String getTypeName() {
		return TYPE_NAME;
	}

	public static String getDatePattern() {
		return DATE_PATTERN;
	}
	
}
