package com.example.nCentrala.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.converter.RegistrationFiledsToUserConverter;
import com.example.nCentrala.model.dto.FormFieldsDTO;
import com.example.nCentrala.model.dto.RegistrationFormDTO;
import com.example.nCentrala.model.dto.UserDTO;
import com.example.nCentrala.service.UserService;

@Service
public class RegistrationDelegate implements JavaDelegate {

	private RuntimeService runtimeService;
	
	private IdentityService identityService;
	
	@Autowired
	private RegistrationFiledsToUserConverter converter;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		//identityService = execution.getProcessEngine().getIdentityService();
		//runtimeService = execution.getProcessEngine().getRuntimeService();
		//User userWithEmail = identityService.createUserQuery().userEmail(execution.getVariable("email").toString()).singleResult();
		
		RegistrationFormDTO regDto = (RegistrationFormDTO) execution.getVariable("registrationForm");
		System.out.println("niiiiz: " + regDto.getFormFields().size());
		
		UserDTO user = converter.convert(regDto.getFormFields());
		
		com.example.nCentrala.model.User u = userService.findUserByUsername(user.getUsername());
		
		if(u== null && validateRegistrationData(user))
		{
			com.example.nCentrala.model.User userData = new com.example.nCentrala.model.User(user); //fali dodela uloge, kad proradi dodati
			//fali i slanje email-a
			userService.saveUser(userData);
			
			execution.setVariable("valid", true);
		}else
		{
			execution.setVariable("valid", false);
		}
	}
	
	private boolean validateRegistrationData(UserDTO user) {
		
		if(user.getName() == null || user.getSurname() == null || user.getCity() == null || user.getState() == null ||
				user.getEmail() == null || user.getPassword() == null || user.getPassword2() == null)
		{
			return false;
			
		}else if(!user.getPassword().equals(user.getPassword2()))
		{
			return false;
			
		}else 
		{
			return true;
		}
		
	}

}
