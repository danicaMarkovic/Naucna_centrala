package com.example.nCentrala.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.nCentrala.modelElastic.RejectedArticleIndex;

public interface RejectedArticleIndexRepository extends ElasticsearchRepository<RejectedArticleIndex, Long> {

}
