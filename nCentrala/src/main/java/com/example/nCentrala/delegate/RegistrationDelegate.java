package com.example.nCentrala.delegate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.nCentrala.converter.RegistrationFiledsToUserConverter;
import com.example.nCentrala.model.Role;
import com.example.nCentrala.model.RoleName;
import com.example.nCentrala.model.dto.FormFieldsDTO;
import com.example.nCentrala.model.dto.UserTaskFormDTO;
import com.example.nCentrala.model.dto.UserDTO;
import com.example.nCentrala.service.RoleService;
import com.example.nCentrala.service.UserService;


@Service
public class RegistrationDelegate implements JavaDelegate {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private RegistrationFiledsToUserConverter converter;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		UserTaskFormDTO regDto = (UserTaskFormDTO) execution.getVariable("registrationForm");
		UserDTO user = converter.convert(regDto.getFormFields());
		
		Optional<com.example.nCentrala.model.User> u = userService.findUserByUsername(user.getUsername());
		
		com.example.nCentrala.model.User u2 = userService.findUserByEmail(user.getEmail());
		
		
		if(u.isEmpty() && u2 == null && validateRegistrationData(user)) {
			
			com.example.nCentrala.model.User userData = new com.example.nCentrala.model.User(user); 
			
			userData.setActivated(false);
			userData.setPassword(encoder.encode(user.getPassword()));
			
			Set<Role> roles = new HashSet<>();
			Role r1 = roleService.getRoleByName(RoleName.ROLE_USER);
			roles.add(r1);
			
			userData.setRoles(roles);
			
			userService.saveUser(userData);
			
			//dodavanje u camundinu tabelu user-a
			User newUser = identityService.newUser(execution.getVariable("username").toString());
			newUser.setEmail(user.getName());
			newUser.setFirstName(user.getName());
			newUser.setLastName(user.getSurname());
			newUser.setPassword(user.getPassword());
			identityService.saveUser(newUser);
			
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
			
		}else if(!this.isValidEmailAddress(user.getEmail()))
		{
			return false;
		}
		else if(!user.getPassword().equals(user.getPassword2()))
		{
			return false;
			
		}else 
		{
			return true;
			
		}
		
	}
	
	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }

}
