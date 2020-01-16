package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.converter.RegistrationFiledsToUserConverter;
import com.example.nCentrala.model.User;
import com.example.nCentrala.model.dto.UserDTO;
import com.example.nCentrala.model.dto.UserTaskFormDTO;
import com.example.nCentrala.service.UserService;

@Service
public class ActivateUserAfterEmailDelegate implements JavaDelegate {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationFiledsToUserConverter converter;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		UserTaskFormDTO regDto = (UserTaskFormDTO) execution.getVariable("registrationForm");
		
		UserDTO userInfo = converter.convert(regDto.getFormFields());
		
		User user = userService.findUserByUsername(userInfo.getUsername()).get();
		
		user.setActivated(true);
		
		userService.saveUser(user);
	}

}
