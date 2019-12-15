package com.example.nCentrala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRep;
}
