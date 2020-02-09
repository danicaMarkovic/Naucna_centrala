package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.nCentrala.jwt.JwtProvider;
import com.example.nCentrala.model.Editor;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.User;
import com.example.nCentrala.service.JournalService;
import com.example.nCentrala.service.UserService;

@Service
public class SendingEmailsAndChooseEditor implements JavaDelegate {
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserService userService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		//odabir mainEditor-a
		String issn = (String) execution.getVariable("journals");
		
		Journal journal = journalService.getJournalByIssn(issn);
		
		Editor editor = journal.getMainEditor();
		
		//setovanje glavnog urednika zbog assigne-a
		execution.setVariable("mainEditor", editor.getUsername());
		
		//editor
		//this.sendEmail(editor.getEmail(), editor.getUsername(), journal.getName());
		
		//autor
		String username = jwtProvider.getUsernameLoggedUser();
		User user = userService.findUserByUsername(username).get();
		//this.sendEmail(user.getEmail(), user.getUsername(), journal.getName());
	}
	
	private void sendEmail(String email, String username, String name) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("New article added");
		mail.setText("Welcome " + username + ",\n New article is added for journal " + name  + " !" + "\n Nc team.");
		
		javaMailSender.send(mail);
	}

}
