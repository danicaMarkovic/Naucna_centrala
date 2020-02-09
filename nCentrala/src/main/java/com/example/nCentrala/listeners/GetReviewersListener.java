package com.example.nCentrala.listeners;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.UserService;

@Service
public class GetReviewersListener implements TaskListener {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		TaskFormData taskFormData = delegateTask.getExecution().getProcessEngineServices()
				.getFormService().getTaskFormData(delegateTask.getId());
		
		List<FormField> formFields = taskFormData.getFormFields();
		
		Article article = articleService.getLastInsertedArticle();
		
		List<Reviewer> users = userService.getJournalReviewers(article.getJournal().getId());
		
		if(formFields != null)
		{
			for(FormField field : formFields)
			{
				if(field.getId().equals("choosenReviewers"))
				{
					HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");
					items.clear();
					
					for(Reviewer r : users)
					{
						items.put(r.getUsername(), r.getName() + " " + r.getSurname());
					}
				}
			}
		}

		
	}

}
