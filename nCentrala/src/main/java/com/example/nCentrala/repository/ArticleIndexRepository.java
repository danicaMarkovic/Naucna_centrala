package com.example.nCentrala.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.nCentrala.modelElastic.AcceptedArticleIndex;

public interface ArticleIndexRepository extends ElasticsearchRepository<AcceptedArticleIndex, Long> {

}
