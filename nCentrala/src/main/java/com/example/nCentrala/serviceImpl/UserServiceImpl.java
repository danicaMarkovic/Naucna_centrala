package com.example.nCentrala.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.RoleName;
import com.example.nCentrala.model.User;
import com.example.nCentrala.repository.UserRepository;
import com.example.nCentrala.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<User> getUserByRole(RoleName name) {
		// TODO Auto-generated method stub
		return userRepository.findByRoles_Name(name);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
	}

}
