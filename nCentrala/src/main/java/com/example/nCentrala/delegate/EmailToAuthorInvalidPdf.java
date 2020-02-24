package com.example.nCentrala.delegate;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.nCentrala.jwt.JwtProvider;
import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.ArticleStatus;
import com.example.nCentrala.model.User;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.UserService;

@Service
public class EmailToAuthorInvalidPdf implements JavaDelegate {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;

	@Autowired
	private ArticleService articleService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String name = execution.getCurrentActivityName();
		
		String username = (String) execution.getVariable("initiator"); //slanje email-a autoru kao inicijatoru procesa
		
		User user = userService.findUserByUsername(username).get();
		
		Article article = articleService.getLastInsertedArticle();
		
		//email za ponovni upload pdfa
		if(name.equals("Invalid pdf email"))
		{
//			Date deadline = (Date) execution.getVariable("deadline");
//			
//			SimpleMailMessage mail = new SimpleMailMessage();
//			mail.setTo(user.getEmail());
//			mail.setFrom(env.getProperty("spring.mail.username"));
//			mail.setSubject("Pdf format invalid");
//			mail.setText("Hi, " + user.getUsername() + ",\n Pdf format that you uploaded for article " + article.getTitle() +" Please loggin to upload a new one, until " + deadline.toString() +".\n Nc team.");
//			
//			javaMailSender.send(mail);
			
		}else if(name.equals("Article rejected email")) //email za odbijanje rada
		{
			

//			SimpleMailMessage mail = new SimpleMailMessage();
//			mail.setTo(user.getEmail());
//			mail.setFrom(env.getProperty("spring.mail.username"));
//			mail.setSubject("Pdf format invalid");
//			mail.setText("Hi, " + user.getUsername() + ",\n Unfortunately deadline has already passed, so your article " + article.getTitle() + " has been refused.\n Nc team.");
//			
//			javaMailSender.send(mail);
//			
			article.setAccepted(ArticleStatus.REJECTED);
			articleService.saveArticle(article);
			
		}else if(name.equals("Emails notification for rejecting"))
		{
//			SimpleMailMessage mail = new SimpleMailMessage();
//			mail.setTo(user.getEmail());
//			mail.setFrom(env.getProperty("spring.mail.username"));
//			mail.setSubject("Pdf format invalid");
//			mail.setText("Hi, " + user.getUsername() + ",\n Unfortunately deadline has already passed, so your article " + article.getTitle() + " has been refused.\n Nc team.");
//			
//			javaMailSender.send(mail);
//			
			article.setAccepted(ArticleStatus.REJECTED);
			articleService.saveArticle(article);
		}
		else if(name.equals("Save data and send email to author"))
		{
			
//			SimpleMailMessage mail = new SimpleMailMessage();
//			mail.setTo(user.getEmail());
//			mail.setFrom(env.getProperty("spring.mail.username"));
//			mail.setSubject("Article accepted");
//			mail.setText("Hi, " + user.getUsername() + ",\n Your article \"" + article.getTitle() + "\" has been accepted. \n Nc team.");
//			
//			javaMailSender.send(mail);
			
			article.setAccepted(ArticleStatus.ACCEPTED);
			articleService.saveArticle(article);
			
		}else if(name.equals("Save data after rejecting")) //odbijanje casopisa od strane editora
		{
			article.setAccepted(ArticleStatus.REJECTED);
			articleService.saveArticle(article);
		
		}else if(name.equals("Send notification for changes")) //male ili velike izmene
		{
			
//			SimpleMailMessage mail = new SimpleMailMessage();
//			mail.setTo(user.getEmail());
//			mail.setFrom(env.getProperty("spring.mail.username"));
//			mail.setSubject("Changes to article");
//			mail.setText("Hi, " + user.getUsername() + ",\n Your article \"" + article.getTitle() + "\" needs to be changed before accepting. Please login to see comment from reviewers.\n Nc team.");
//			
//			javaMailSender.send(mail);
			
		}else if(name.equals("Deadline passed notification"))
		{
//			SimpleMailMessage mail = new SimpleMailMessage();
//			mail.setTo(user.getEmail());
//			mail.setFrom(env.getProperty("spring.mail.username"));
//			mail.setSubject("Deadline passed");
//			mail.setText("Hi, " + user.getUsername() + ",\n We are sorry, but deadline for article correction has been passed. Your article \"" + article.getTitle() + "\" has been rejected.\n Nc team.");
//			
//			javaMailSender.send(mail);
			
			article.setAccepted(ArticleStatus.REJECTED);
			articleService.saveArticle(article);
		}else if(name.equals("Save data after rejecting and email sending"))
		{
			article.setAccepted(ArticleStatus.REJECTED);
			articleService.saveArticle(article);
		}
	}

}
