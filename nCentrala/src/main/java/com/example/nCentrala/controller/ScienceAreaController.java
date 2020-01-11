package com.example.nCentrala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.model.ScienceArea;
import com.example.nCentrala.service.ScienceAreaService;

@RestController
@RequestMapping("area")
@CrossOrigin(origins = "http://localhost:1337")
public class ScienceAreaController {
	
	@Autowired
	private ScienceAreaService service;
	
	@RequestMapping(
			value="all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ScienceArea>> getAllAreas(){
		
		return new ResponseEntity<> (service.getAllAreas(), HttpStatus.OK);
	}

}
