package com.example.nCentrala.delegate;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.jwt.JwtProvider;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.Subscription;
import com.example.nCentrala.model.User;
import com.example.nCentrala.service.JournalService;
import com.example.nCentrala.service.SubscriptionService;
import com.example.nCentrala.service.UserService;

@Service
public class PayMembership implements JavaDelegate {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private SubscriptionService subService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		
		if(Math.random() < 0.8){ //uspesno placanje
			
			String username = jwtProvider.getUsernameLoggedUser();
			String issn = (String) execution.getVariable("journals");
			
			User user = userService.findUserByUsername(username).get();
			
			Journal journal = journalService.getJournalByIssn(issn);
			
			Subscription sub = new Subscription(journal.getPrice(),new Date(),user,journal);
			subService.saveSubscription(sub);
			
			System.out.println("Uspesno placanje");
			execution.setVariable("paymentSuccess", true);
			
		}else 					//neuspesno placanje
		{
			System.out.println("Neuspesno placanje");
			execution.setVariable("paymentSuccess", false);
		}
	}

}
