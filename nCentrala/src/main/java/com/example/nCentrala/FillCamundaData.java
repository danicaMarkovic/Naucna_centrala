package com.example.nCentrala;

import java.util.List;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nCentrala.service.UserService;



@Component
public class FillCamundaData {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdentityService identityService;

	
	@PostConstruct
	private void init() throws Exception {
		
		List<com.example.nCentrala.model.User> users = userService.getAll();
		
		this.saveCamundaUser(users);
		
	}
	
	public void saveCamundaUser (List<com.example.nCentrala.model.User> users){
		
		for(com.example.nCentrala.model.User user : users)
		{
			User u = identityService.createUserQuery().userIdIn(user.getUsername()).singleResult();
			
			if(u == null)
			{
				User newUser = identityService.newUser(user.getUsername());
				newUser.setEmail(user.getEmail());
				newUser.setFirstName(user.getEmail());
				newUser.setLastName(user.getSurname());
				newUser.setPassword(user.getPassword());
				identityService.saveUser(newUser);
			}
			
		}

	}
	
}
