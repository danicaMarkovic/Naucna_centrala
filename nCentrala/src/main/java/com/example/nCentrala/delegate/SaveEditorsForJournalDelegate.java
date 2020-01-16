package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Editor;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.service.JournalService;
import com.example.nCentrala.service.UserService;

@Service
public class SaveEditorsForJournalDelegate implements JavaDelegate {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JournalService journalService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String editors = (String) execution.getVariable("editors");
		String issn = (String) execution.getVariable("issn");

		Journal journal = journalService.getJournalByIssn(issn);
		
		String parts[] = editors.split(",");
		
		for(String part : parts)
		{
			Editor e = (Editor) userService.findUserByUsername(part).get();
			e.setJournal(journal);
			userService.saveUser(e);
			
		}
	}

}
