package com.example.nCentrala.delegate;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.service.UserService;

@Service
public class AddMainEditorToListDelegate implements JavaDelegate {
	
	@Autowired
	private UserService userService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String username = (String) execution.getVariable("mainEditor");
		System.out.println("Glavni urednik jee " +username);
		
		List<String> ret = new ArrayList<String>();
		ret.add(username);
		
		execution.setVariable("reviewersList", ret);
	}

}
