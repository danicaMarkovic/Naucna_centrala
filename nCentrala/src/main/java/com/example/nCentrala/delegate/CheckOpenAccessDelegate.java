package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Journal;
import com.example.nCentrala.service.JournalService;

@Service
public class CheckOpenAccessDelegate implements JavaDelegate {
	
	@Autowired
	private JournalService journalService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String issn = (String) execution.getVariable("journals");
		
		Journal journal = journalService.getJournalByIssn(issn);
		
		if(journal.isOpenAccess())
		{
			execution.setVariable("openAccess", true);
		}else
		{
			execution.setVariable("openAccess", false);
		}
	}

}
