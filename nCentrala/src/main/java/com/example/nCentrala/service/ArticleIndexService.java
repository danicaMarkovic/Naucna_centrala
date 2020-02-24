package com.example.nCentrala.service;

import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.dto.ArticleIndexDTO;
import com.example.nCentrala.modelElastic.AcceptedArticleIndex;

public interface ArticleIndexService {

	public Iterable<AcceptedArticleIndex> findAll();
	public boolean addArticle(AcceptedArticleIndex article);
	public boolean updateArticle(AcceptedArticleIndex article);
	public String getTextFromPdfDocument(PDDocument document);
	public boolean addDataToIndex(Article article, String coauthors, String author);
	public Optional<AcceptedArticleIndex> getById(Long id);
}
