package com.example.nCentrala.controller;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.dto.FormFieldsDTO;
import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.model.dto.RegistrationFormDTO;
import com.example.nCentrala.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:1337")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@RequestMapping(
			value="startRegistration",
			method = RequestMethod.GET)
	public ResponseEntity<?>  getFieldsForRegistration() {
		
		//startovanje procesa
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("NewRegistration");
		
		//uzimanje task-a
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new ResponseEntity<>(new FormFieldsDTO(task.getId(), properties, pi.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(
			value="finishRegistration",
			method = RequestMethod.POST,
			produces = "application/json")
	public ResponseEntity<?> finishRegistrationProcess(@RequestBody RegistrationFormDTO regDto){
		
		Task task = taskService.createTaskQuery().taskId(regDto.getTaskId()).singleResult();
		
		HashMap<String, Object> map = mapListToDto(regDto.getFormFields());
		
		System.out.println("taskID: " + regDto.getTaskId() + "prosID: " + regDto.getProcessId() + "niz: "+regDto.getFormFields().size());
		
		runtimeService.setVariable(task.getProcessInstanceId(), "registrationForm", regDto);
		formService.submitTaskForm(regDto.getTaskId(), map);
		
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
