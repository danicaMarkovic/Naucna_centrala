package com.example.nCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nCentrala.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:1337")
public class UserController {
	
	@Autowired
	private UserService userService;

}
