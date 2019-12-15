package com.example.nCentrala.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.nCentrala.modelElastic.ArticleDoc;

public interface ArticleDocRepository extends ElasticsearchRepository<ArticleDoc, Long> {

}
