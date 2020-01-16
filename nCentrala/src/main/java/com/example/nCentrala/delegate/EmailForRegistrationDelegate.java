package com.example.nCentrala.delegate;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.model.dto.UserTaskFormDTO;

@Service
public class EmailForRegistrationDelegate implements JavaDelegate {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		UserTaskFormDTO regDto = (UserTaskFormDTO) execution.getVariable("registrationForm");
		
		List<FormSubmissionDTO> formFields = regDto.getFormFields();
		String email = "";
		String username = "";
		
		for(FormSubmissionDTO field : formFields)
		{
			if(field.getFieldId().equals("email"))
			{
				email = field.getFieldValue();
			}
			
			if(field.getFieldId().equals("username"))
			{
				username = field.getFieldValue();
			}
		}
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Registration confirmation");
		mail.setText("Welcome " + username + ",\n Thank you for using Nc app!"
				+ "To activate your account, click on:  "+"http://localhost:1337/activate/" + regDto.getProcessId() + "\n Nc team.");
		
		javaMailSender.send(mail);
		
		System.out.println("Email poslat!");
		
	}

}
