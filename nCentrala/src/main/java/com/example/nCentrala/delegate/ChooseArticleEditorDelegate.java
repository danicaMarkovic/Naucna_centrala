package com.example.nCentrala.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Editor;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.service.ArticleService;

@Service
public class ChooseArticleEditorDelegate implements JavaDelegate {
	
	@Autowired
	private ArticleService artService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		Article article = artService.getLastInsertedArticle();
		String area = (String) execution.getVariable("articleScienceArea");
		
		List<Editor> editors = article.getJournal().getEditors();
		Editor editor = null; 
		Editor mainEditor = article.getJournal().getMainEditor();
		
		for(Editor e : editors) {
			
			for(ScienceArea a : e.getAreasOfInterest())
			{
				if(a.getName().equals(area))
				{
					editor = e;
					
					break;
				}
			}
		}
		
		if(editor == null)
		{
			execution.setVariable("editor", article.getJournal().getMainEditor().getUsername());
			//this.sendEmail(mainEditor.getEmail(), mainEditor.getUsername(), article.getTitle());
			System.out.println("Nema editoraa, preuzima glavnii");
		}else
		{
			execution.setVariable("editor", editor.getUsername());
			//this.sendEmail(editor.getEmail(), editor.getUsername(), article.getTitle());
			System.out.println("Editor je " + editor.getUsername());
		}
	}
	
	private void sendEmail(String email, String username,String article)
	{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Article notification");
		mail.setText("Hi " + username + ",\n Please login and choose reviewers for article "+ article + "\n Nc team.");
		
		javaMailSender.send(mail);
	}

}
