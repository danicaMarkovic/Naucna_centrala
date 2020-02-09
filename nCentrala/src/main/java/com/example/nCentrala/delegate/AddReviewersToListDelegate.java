package com.example.nCentrala.delegate;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.service.UserService;

@Service
public class AddReviewersToListDelegate implements JavaDelegate {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdentityService identityService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String name = execution.getCurrentActivityName();
		
		if(name.equals("Add choosen reviewers to list"))
		{
			String reviewers = (String) execution.getVariable("choosenReviewers");
			
			List<String> ret = this.addReviewerToList(reviewers);
			
			execution.setVariable("reviewersList", ret);
			
		}else if(name.equals("Save new reviewer"))
		{
			String newOne = (String) execution.getVariable("newReviewer");
			
			List<String> ret = this.addReviewerToList(newOne);
			
			execution.setVariable("reviewersList", ret);
		}

	}
	
	private List<String> addReviewerToList(String reviewers){
		
		String[] rev = reviewers.split(",");
		
		List<String> ret = new ArrayList<String>();
		
		for(String s : rev)
		{
			ret.add(s);
		}
		
		return ret;
	}
	
	
	private List<User> getCamundaUsers(String reviewers){
		
		String[] rev = reviewers.split(",");
		List<User> ret = new ArrayList<User>();
		
		for(String r : rev)
		{
			User user = identityService.createUserQuery().userIdIn(r).singleResult();
			ret.add(user);
		}
		
		return ret;
	}

}
