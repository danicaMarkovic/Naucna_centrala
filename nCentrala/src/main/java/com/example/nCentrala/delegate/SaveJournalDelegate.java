package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.converter.JournalFormToJournalConverter;
import com.example.nCentrala.model.Editor;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.User;
import com.example.nCentrala.model.dto.UserTaskFormDTO;
import com.example.nCentrala.service.JournalService;
import com.example.nCentrala.service.UserService;

@Service
public class SaveJournalDelegate implements JavaDelegate {
	
	@Autowired
	private JournalFormToJournalConverter converter;
	

	@Autowired
	private JournalService journalService;
	
	@Autowired
	private UserService userService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		UserTaskFormDTO journalTaskDto = (UserTaskFormDTO) execution.getVariable("newJournalForm");
		
		String username = (String) execution.getVariable("initiator");
		
		Editor user = (Editor) userService.findUserByUsername(username).get();
		
		Journal journal = converter.convert(journalTaskDto.getFormFields());
		
		if(journal != null)
		{
			journal.setActivated(false);
			journal.setMainEditor(user);
			Journal j = journalService.saveJournal(journal);
			execution.setVariable("magazineActivated", false);
		}
	}

}
