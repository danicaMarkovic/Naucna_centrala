package com.example.nCentrala.listeners;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Journal;
import com.example.nCentrala.service.JournalService;

@Service
public class GetJournalsListener implements TaskListener {

	@Autowired
	private JournalService journalService;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		TaskFormData taskFormData = delegateTask.getExecution().getProcessEngineServices()
				.getFormService().getTaskFormData(delegateTask.getId());
		
		List<FormField> formFields = taskFormData.getFormFields();
		if(formFields != null)
		{
			
			for(FormField field : formFields)
			{
				
				if(field.getId().equals("journals"))
				{
					HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");
					items.clear();
					
					for(Journal j : journalService.activeJournals())
					{
						items.put(j.getIssn(), j.getName());
					}
				}
			}
		}
	}

}
