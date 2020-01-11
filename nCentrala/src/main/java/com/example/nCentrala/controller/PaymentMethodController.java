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

import com.example.nCentrala.model.PaymentMethod;
import com.example.nCentrala.service.PaymentMethodService;

@RestController
@RequestMapping("method")
@CrossOrigin(origins = "http://localhost:1337")
public class PaymentMethodController {
	
	@Autowired
	private PaymentMethodService service;
	
	@RequestMapping(value="all",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PaymentMethod>> getAllMethods(){
		
		return new ResponseEntity<> (service.getAllMethods(), HttpStatus.OK);
	}

}
