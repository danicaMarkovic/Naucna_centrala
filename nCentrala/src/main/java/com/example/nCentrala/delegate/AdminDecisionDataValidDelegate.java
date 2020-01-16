package com.example.nCentrala.delegate;

import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.jwt.JwtProvider;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.service.JournalService;


@Service
public class AdminDecisionDataValidDelegate implements JavaDelegate {
	
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String issn = (String) execution.getVariable("issn");
		boolean adminDecision  = (boolean) execution.getVariable("dataCorrect");
		
		if(!adminDecision)
		{
			Journal j = journalService.getJournalByIssn(issn);
			journalService.deleteJournal(j);
			System.out.println("Rekao je false");
			execution.setVariable("dataCorrect", false);
		}else
		{
			execution.setVariable("dataCorrect", true);
			System.out.println("Kaze sve je ok");
		}
	}
	
	

}
