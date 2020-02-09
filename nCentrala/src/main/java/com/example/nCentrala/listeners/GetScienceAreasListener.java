package com.example.nCentrala.listeners;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.service.ScienceAreaService;
import com.example.nCentrala.model.ScienceArea;

@Service
public class GetScienceAreasListener implements TaskListener {

	@Autowired
	private ScienceAreaService areaService;
	
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
				if(field.getId().equals("articleScienceArea"))
				{
					HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");
					items.clear();
					
					for(ScienceArea a : areaService.getAllAreas())
					{
						items.put(a.getName(), a.getName());
					}
				}
			}
		}
	}

}
