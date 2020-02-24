package com.example.nCentrala.modelElastic;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = ArticleReviewIndex.INDEX_NAME, type = ArticleReviewIndex.TYPE_NAME, shards = 1, replicas = 0)
public class ArticleReviewIndex {

	public static final String INDEX_NAME = "articlereviewindex";
	public static final String TYPE_NAME = "articlereview";
	
	@Id
	private Long id;
	
	@Field(type = FieldType.Long, store  = true)
	private Long reviewerId;
	
	@Field(type = FieldType.Long, store  = true)
	private Long journalId;
	
	@Field(type = FieldType.Text, store = true, analyzer = "serbian")
	private String articleTitle;
	
	@Field(type = FieldType.Text, store = true, analyzer = "serbian")
	private String content;
	
	@Field(type = FieldType.Boolean, store  = true)
	private boolean accepted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}

	public Long getJournalId() {
		return journalId;
	}

	public void setJournalId(Long journalId) {
		this.journalId = journalId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	
	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public String toString() {
		return "ArticleReviewIndex [\n id=" + id + ",\n reviewerId=" + reviewerId + ",\n articleTitle=" + articleTitle + "]";
	}
	
	
}
