package com.example.nCentrala.service;

import java.util.List;
import java.util.Optional;

import com.example.nCentrala.model.Reviewer;
import com.example.nCentrala.model.RoleName;
import com.example.nCentrala.model.User;

public interface UserService {

	Optional<User> findUserByUsername(String username);
	
	User saveUser(User user);
	
	List<User> getUserByRole(RoleName name);
	
	List<User> getAll();
	
	void deleteUser(User user);
	
	User findUserByEmail(String email);
	
	List<Reviewer> getJournalReviewers(Long journalId);
}
