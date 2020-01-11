package com.example.nCentrala.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.model.dto.UserTaskFormDTO;
import com.example.nCentrala.service.JournalService;

@RestController
@RequestMapping("journal")
@CrossOrigin(origins = "http://localhost:1337")
public class JournalController {

	@Autowired
	private JournalService journalService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private FormService formService;
	
	@RequestMapping(
			value="finish",
			method = RequestMethod.POST,
			produces = "application/json",
			consumes = "application/json")
	public ResponseEntity<?> finishProcess(@RequestBody UserTaskFormDTO taskDto){
		System.out.println("Usao");
		Task task = taskService.createTaskQuery().taskId(taskDto.getTaskId()).singleResult();
		
		HashMap<String, Object> map = mapListToDto(taskDto.getFormFields());
		
		System.out.println("taskID: " + taskDto.getTaskId() + "prosID: " + taskDto.getProcessId() + "niz: "+taskDto.getFormFields().size());
		
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
