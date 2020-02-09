package com.example.nCentrala.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.nCentrala.modelElastic.ReviewerIndex;

public interface ReviewerIndexRepository extends ElasticsearchRepository<ReviewerIndex, Long> {

}
