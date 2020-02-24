package com.example.nCentrala.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.nCentrala.modelElastic.ArticleReviewIndex;

public interface ArticleReviewIndexRepository extends ElasticsearchRepository<ArticleReviewIndex, Long> {

}
