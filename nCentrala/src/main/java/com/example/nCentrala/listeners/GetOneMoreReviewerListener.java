//package com.example.nCentrala.listeners;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.camunda.bpm.engine.delegate.DelegateTask;
//import org.camunda.bpm.engine.delegate.TaskListener;
//import org.camunda.bpm.engine.form.FormField;
//import org.camunda.bpm.engine.form.TaskFormData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.nCentrala.model.Article;
//import com.example.nCentrala.model.Reviewer;
//import com.example.nCentrala.model.RoleName;
//import com.example.nCentrala.model.User;
//import com.example.nCentrala.service.ArticleService;
//import com.example.nCentrala.service.UserService;
//
//@Service
//public class GetOneMoreReviewerListener implements TaskListener {
//	
//	@Autowired
//	private ArticleService articleService;
//	
//	@Autowired
//	private UserService userService;
//
//	@Override
//	public void notify(DelegateTask delegateTask) {
//		// TODO Auto-generated method stub
//
//		TaskFormData taskFormData = delegateTask.getExecution().getProcessEngineServices()
//				.getFormService().getTaskFormData(delegateTask.getId());
//		
//		List<FormField> formFields = taskFormData.getFormFields();
//		
//		Article article = articleService.getLastInsertedArticle();
//		
//		List<Reviewer> users = userService.getJournalReviewers(article.getJournal().getId());
//		List<User> allReviewers = userService.getUserByRole(RoleName.ROLE_REVIEWER);
//		
//		if(formFields != null)
//		{
//			for(FormField field : formFields)
//			{
//				if(field.getId().equals("newReviewer"))
//				{
//					HashMap<String, String> items = (HashMap<String, String>) field.getType().getInformation("values");
//					items.clear();
//					
//					List<User> usersList = this.getOneMoreReviewer(users, allReviewers);
//					
//					for(User u : usersList)
//					{
//						items.put(u.getUsername(), u.getName() + " " + u.getSurname());
//					}
//				}
//			}
//		}
//	}
//	
//	private List<User> getOneMoreReviewer(List<Reviewer> reviewers, List<User> users)
//	{
//		List<User> ret = new ArrayList<User>();
//		
//		for(User r  : reviewers)
//		{
//			if(!users.contains(r))
//			{
//				ret.add(r);
//			}
//		}
//		
//		return ret;
//	}
//
//}
