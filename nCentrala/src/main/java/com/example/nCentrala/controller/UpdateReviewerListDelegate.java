package com.example.nCentrala.controller;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class UpdateReviewerListDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String newReviewer = (String) execution.getVariable("replaceReviewer");
		
		execution.setVariable("oneReviewer", newReviewer);
	}

}
