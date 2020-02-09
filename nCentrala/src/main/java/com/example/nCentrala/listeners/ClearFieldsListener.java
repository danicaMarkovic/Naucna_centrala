package com.example.nCentrala.listeners;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.stereotype.Service;

@Service
public class ClearFieldsListener implements TaskListener {

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
				if(field.getId().equals("authorComment") || field.getId().equals("editorComment"))
				{
					HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");
					items.clear();
				}
			}
		}
				
	}

}
