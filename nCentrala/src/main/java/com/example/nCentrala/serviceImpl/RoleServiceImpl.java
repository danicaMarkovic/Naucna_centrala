package com.example.nCentrala.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nCentrala.model.Role;
import com.example.nCentrala.model.RoleName;
import com.example.nCentrala.repository.RoleRepository;
import com.example.nCentrala.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository repository;

	@Override
	public Role getRoleByName(RoleName name) {
		// TODO Auto-generated method stub
		return repository.findByName(name);
	}

}
