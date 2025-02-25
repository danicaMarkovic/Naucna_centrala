package com.example.nCentrala.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidationException;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.jwt.JwtProvider;
import com.example.nCentrala.model.dto.FormFieldsDTO;
import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.model.dto.TaskDTO;
import com.example.nCentrala.model.dto.UserTaskFormDTO;

@RestController
@RequestMapping("task")
@CrossOrigin(origins = "http://localhost:1337")
public class CamundaTaskController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	private String registrationProcessKey = "NewRegistration";
	
	private String newJournalProcessKey = "NewJournal";
	
	private String journalEditing = "JournalEditing";
	
	
	@RequestMapping(
				value="startRegistrationProcess",
				method = RequestMethod.GET)
	public ResponseEntity<?>  getFieldsForRegistration() {
			
			//startovanje procesa
			ProcessInstance pi = runtimeService.startProcessInstanceByKey(this.registrationProcessKey);
			
			//uzimanje task-a
			Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
			
			TaskFormData tfd = formService.getTaskFormData(task.getId());
			List<FormField> properties = tfd.getFormFields();
			
			
			return new ResponseEntity<>(new FormFieldsDTO(task.getName(), task.getId(), properties, pi.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(
			value="finishRegistration",
			method = RequestMethod.POST,
			produces = "application/json")
	public ResponseEntity<?> finishRegistrationProcess(@RequestBody UserTaskFormDTO regDto){
		
		Task task = taskService.createTaskQuery().taskId(regDto.getTaskId()).singleResult();
		
		HashMap<String, Object> map = mapListToDto(regDto.getFormFields());
		
		runtimeService.setVariable(task.getProcessInstanceId(), "registrationForm", regDto);
		formService.submitTaskForm(regDto.getTaskId(), map);
		
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@RequestMapping(
			value="startJournalProcess",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDITOR')")
	public ResponseEntity<?>  startProcessAndSetStarterVar() {
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("NewJournal");
		
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value="startEditingProcess",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('AUTHOR')")
	public ResponseEntity<?> startJournalEditingProcess(){
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("JournalEditing");
		
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/get/tasks/{processInstanceId}",
							method = RequestMethod.GET,
							produces = "application/json")
	public  ResponseEntity<List<TaskDTO>> get(@PathVariable String processInstanceId) {
					
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskDTO> dtos = new ArrayList<TaskDTO>();
		for (Task task : tasks) {
			TaskDTO t = new TaskDTO(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
					
		return new ResponseEntity(dtos,  HttpStatus.OK);
	}
	
	@RequestMapping(
				value="finishTask/{id}",
				method = RequestMethod.POST,
				produces = "application/json")
	public ResponseEntity<?> finishRegistrationProcess(@PathVariable("id")String taskId, @RequestBody List<FormSubmissionDTO> dto){
			
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		HashMap<String, Object> map = mapListToDto(dto);
			
		try {
			formService.submitTaskForm(taskId, map);
		}catch (FormFieldValidationException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
		return new ResponseEntity<> (HttpStatus.OK);
			
	}
	//ovde dodati proveru assigneee
	@RequestMapping(
			value="getTaskField/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<?>  getFormFields(@PathVariable("id") String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		TaskFormData tfd = formService.getTaskFormData(taskId);
		
		String assigne = task.getAssignee();
		
		if(assigne != null)
		{
			String username = jwtProvider.getUsernameLoggedUser();
			
			if(!assigne.equals(username))
			{
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		}
		
		List<FormField> properties = tfd.getFormFields();
		
		
		return new ResponseEntity<>(new FormFieldsDTO(task.getName(),task.getId(), properties, task.getProcessInstanceId()), HttpStatus.OK);
	}
	
	@RequestMapping(
			value="getActiveTasks",
			method = RequestMethod.GET)
	public ResponseEntity<List<TaskDTO>>  getActiveTasksForCurrentUser() {
		
		List<Task> tasks1 = taskService.createTaskQuery().processDefinitionKey(this.registrationProcessKey)
								.taskAssignee(jwtProvider.getUsernameLoggedUser()).list();
		
		List<Task> tasks2 = taskService.createTaskQuery().processDefinitionKey(this.newJournalProcessKey)
				.taskAssignee(jwtProvider.getUsernameLoggedUser()).list();
		
		List<Task> tasks3 = taskService.createTaskQuery().processDefinitionKey(this.journalEditing)
				.taskAssignee(jwtProvider.getUsernameLoggedUser()).list();
		
		List<TaskDTO> dtos = new ArrayList<TaskDTO>();
		
		for (Task task : tasks1) {
			TaskDTO t = new TaskDTO(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
		
		for (Task task : tasks2) {
			TaskDTO t = new TaskDTO(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
		
		for (Task task : tasks3) {
			TaskDTO t = new TaskDTO(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value="finishNewJournal",
			method = RequestMethod.POST,
			produces = "application/json",
			consumes = "application/json")
	public ResponseEntity<?> finishProcess(@RequestBody UserTaskFormDTO taskDto){
		System.out.println("Usao");
		
		//validacija obaveznih polja
		for(FormSubmissionDTO field : taskDto.getFormFields())
		{
			if(field.getFieldId().equals("name") && field.getFieldValue() == null)
			{
				return new ResponseEntity<>(HttpStatus.GONE);
				
			}else if (field.getFieldId().equals("issn") && field.getFieldValue() == null)
			{
				return new ResponseEntity<>(HttpStatus.GONE);
				
			}else if (field.getFieldId().equals("scienceArea") && field.getAreas().size() == 0)
			{
				return new ResponseEntity<>(HttpStatus.GONE);
				
			}else if (field.getFieldId().equals("paymentMethod") && field.getMethods().size() == 0)
			{
				return new ResponseEntity<>(HttpStatus.GONE);
			}
		}
		
		//////////////////////////
		
		Task task = taskService.createTaskQuery().taskId(taskDto.getTaskId()).singleResult();
		
		HashMap<String, Object> map = mapListToDto(taskDto.getFormFields());
		
		runtimeService.setVariable(task.getProcessInstanceId(), "newJournalForm", taskDto);
		
		formService.submitTaskForm(taskDto.getTaskId(), map);
		
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	
	
	private HashMap<String, Object> mapListToDto(List<FormSubmissionDTO> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDTO temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		
		return map;
	}
	
	
}
