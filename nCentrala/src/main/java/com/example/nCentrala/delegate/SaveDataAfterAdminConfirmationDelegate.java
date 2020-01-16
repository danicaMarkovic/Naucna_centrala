package com.example.nCentrala.delegate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.model.User;
import com.example.nCentrala.service.RoleService;
import com.example.nCentrala.service.UserService;
import com.example.nCentrala.model.Role;
import com.example.nCentrala.model.RoleName;

@Service
public class SaveDataAfterAdminConfirmationDelegate implements JavaDelegate {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		boolean adminConfirmation = (boolean) execution.getVariable("confirmation");
		String username = (String) execution.getVariable("username");
		
		if(adminConfirmation == true)
		{
			User user = userService.findUserByUsername(username).get();
			
			Set<Role> roles = new HashSet<Role>();
			
			Role r1 = roleService.getRoleByName(RoleName.ROLE_REVIEWER);
			
			roles.add(r1);
			
			Reviewer reviewer = new Reviewer(user.getName(), user.getSurname(), user.getEmail(), user.getUsername(),
					user.getPassword(), user.getCity(), user.getState(), user.isActivated(), user.getAreasOfInterest(), roles, "Reviewer", true, null);
			
			userService.deleteUser(user); //brisanje podataka o korisniku kao User-u
			userService.saveUser(reviewer); //cuvanje info o korisniku kao Recenzentu
			
		}
		
	}

}
