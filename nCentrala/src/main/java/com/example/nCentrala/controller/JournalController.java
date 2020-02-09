package com.example.nCentrala.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.dto.FormSubmissionDTO;
import com.example.nCentrala.model.dto.JournalDTO;
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
	
	@RequestMapping(value="/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JournalDTO>> getAllActiveJournals(){
		
		List<JournalDTO> jDTO = new ArrayList<JournalDTO>();
		
		for(Journal j : journalService.activeJournals()) {
			
			jDTO.add(new JournalDTO(j));
		}
		
		return new ResponseEntity<>(jDTO, HttpStatus.OK);
	}
	
}
