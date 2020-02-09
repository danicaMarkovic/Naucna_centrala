package com.example.nCentrala.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Article;
import com.example.nCentrala.model.Review;
import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.model.User;
import com.example.nCentrala.service.ArticleService;
import com.example.nCentrala.service.ReviewService;
import com.example.nCentrala.service.UserService;

@Service
public class GetNewReviewerListener implements TaskListener {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		System.out.println("Usaoooo ");
		TaskFormData taskFormData = delegateTask.getExecution().getProcessEngineServices()
				.getFormService().getTaskFormData(delegateTask.getId());
		
		List<FormField> formFields = taskFormData.getFormFields();

		Article article = articleService.getLastInsertedArticle();
		
		List<Reviewer> reviewers = userService.getJournalReviewers(article.getJournal().getId());
		
		List<Review> reviews = reviewService.getAllByArticle(article.getId());
		
		List<User> ret  = new ArrayList<User>();
		
		for(Reviewer r : reviewers)
		{
			boolean pom = this.alreadyAdded(r.getUsername(), reviews);
			
			if(pom == false)
			{
				System.out.println("False za " + r.getUsername() + " jer je pom: " + pom);
				ret.add(r);
			}
		}
		
		
		if(formFields != null)
		{
			for(FormField field : formFields)
			{
				if(field.getId().equals("replaceReviewer") || field.getId().equals("newReviewer"))
				{
					System.out.println("Pronasao field");
					HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");
					items.clear();
					
					for(User u : ret)
					{
						System.out.println("Dodaje u item " + u.getUsername());
						items.put(u.getUsername(), u.getName() + " " + u.getSurname());
					}
				}
			}
		}
				
		
	}
	

	private boolean alreadyAdded(String username, List<Review> reviews)
	{
		boolean ret = false;
		
		for(Review r : reviews)
		{
			if(r.getUser().getUsername().equals(username))
			{
				ret = true;
				break;
			}
		}
		
		return ret;
	}
}
