package com.example.nCentrala.controller;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.dto.FormFieldsDTO;
import com.example.nCentrala.model.dto.FormSubmissionDTO;
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
	
	@RequestMapping(
			value="startProcess/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<?>  getFieldsForRegistration(@PathVariable("id") String id) {
		
		//startovanje procesa
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(id);
		
		//uzimanje task-a
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new ResponseEntity<>(new FormFieldsDTO(task.getId(), properties, pi.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(
			value="startTask/{id2}",
			method = RequestMethod.GET)
	public ResponseEntity<?>  getFieldsForEmail(@PathVariable("id2") String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new ResponseEntity<>(new FormFieldsDTO(task.getId(), properties, task.getProcessInstanceId()), HttpStatus.OK);
	}
	
	@RequestMapping(
			value="finishTask/{id}",
			method = RequestMethod.GET,
			produces = "application/json")
	public ResponseEntity<?> finishRegistrationProcess(@PathVariable("id")String taskId){
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		formService.submitTaskForm(taskId, null);
		
		return new ResponseEntity<> (HttpStatus.OK);
	}


}
