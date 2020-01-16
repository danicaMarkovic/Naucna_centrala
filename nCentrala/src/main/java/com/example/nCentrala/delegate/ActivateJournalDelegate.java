package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Journal;
import com.example.nCentrala.service.JournalService;

@Service
public class ActivateJournalDelegate implements JavaDelegate {
	
	@Autowired
	private JournalService journalService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Aktivacija casopisa!");
		String issn = (String) execution.getVariable("issn");
		
		Journal journal = journalService.getJournalByIssn(issn);
		
		journal.setActivated(true);
		
		journalService.saveJournal(journal);
		
	}

}
