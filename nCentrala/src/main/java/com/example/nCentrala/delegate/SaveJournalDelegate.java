package com.example.nCentrala.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.converter.JournalFormToJournalConverter;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.dto.UserTaskFormDTO;
import com.example.nCentrala.service.JournalService;

@Service
public class SaveJournalDelegate implements JavaDelegate {
	
	@Autowired
	private JournalFormToJournalConverter converter;
	

	@Autowired
	private JournalService journalService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		UserTaskFormDTO journalTaskDto = (UserTaskFormDTO) execution.getVariable("newJournalForm");
		
		Journal journal = converter.convert(journalTaskDto.getFormFields());
		if(journal != null)
		{
			journal.setActivated(false);
			Journal j = journalService.saveJournal(journal);
			execution.setVariable("magazineActivated", false);
		}
	}

}
