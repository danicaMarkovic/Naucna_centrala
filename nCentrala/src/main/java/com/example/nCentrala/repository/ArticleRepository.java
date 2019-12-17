package com.example.nCentrala.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.nCentrala.modelElastic.ArticleIndex;

public interface ArticleRepository extends ElasticsearchRepository<ArticleIndex, Long> {

}
