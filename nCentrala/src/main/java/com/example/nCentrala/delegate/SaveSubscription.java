package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.nCentrala.service.SubscriptionService;

public class SaveSubscription implements JavaDelegate {
	
	@Autowired
	private SubscriptionService subService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

	}

}
