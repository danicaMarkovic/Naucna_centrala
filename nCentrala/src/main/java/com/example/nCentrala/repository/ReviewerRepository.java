package com.example.nCentrala.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.nCentrala.modelElastic.ReviewerIndex;

public interface ReviewerRepository extends ElasticsearchRepository<ReviewerIndex, Long> {

}
