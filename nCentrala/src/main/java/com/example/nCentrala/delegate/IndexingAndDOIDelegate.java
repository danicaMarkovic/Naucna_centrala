package com.example.nCentrala.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Coauthor;
import com.example.nCentrala.model.User;
import com.example.nCentrala.service.ArticleIndexService;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.CoauthorService;
import com.example.nCentrala.service.UserService;

@Service
public class IndexingAndDOIDelegate implements JavaDelegate {
	
	@Autowired
	private ArticleIndexService indexService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CoauthorService coauthorService;
	
	@Autowired
	private UserService userService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		Article article = articleService.getLastInsertedArticle();
		
		List<Coauthor> coauthors = coauthorService.getAllByArticle(article.getId());
		String coA = "";
		for(Coauthor c : coauthors)
		{
			coA += c.getName() + " " + c.getSurname() + ",";
		}
		//odrstranjivanje poslednjeg zareza
		coA = coA.substring(0, coA.length() - 1);
		
		String username = (String) execution.getVariable("initiator");
		
		User user = userService.findUserByUsername(username).get();
		
		String author = user.getName() + " " + user.getSurname();
		
		boolean ret = indexService.addDataToIndex(article, coA, author);
		
		if(ret)
		{
			System.out.println("Dodato u indeks");
		}else
		{
			System.out.println("Nije dodato u indekssss :((((((");
		}
	}

}
