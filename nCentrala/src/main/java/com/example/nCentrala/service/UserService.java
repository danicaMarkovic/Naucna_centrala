package com.example.nCentrala.service;

import java.util.Optional;

import com.example.nCentrala.model.User;

public interface UserService {

	User findUserByUsername(String username);
	
	User saveUser(User user);
	
}
