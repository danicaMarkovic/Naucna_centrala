package com.example.nCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nCentrala.model.Article;

public interface ArticleJPARepository extends JpaRepository<Article, Long> {

	Article findOneByTitle(String title);
	
	Article findTopByOrderByIdDesc();
}
