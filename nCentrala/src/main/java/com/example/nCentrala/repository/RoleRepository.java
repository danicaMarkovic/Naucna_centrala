package com.example.nCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nCentrala.model.Role;
import com.example.nCentrala.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(RoleName name);
	
}
