package com.example.nCentrala.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nCentrala.model.RoleName;
import com.example.nCentrala.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
	List<User> findByRoles_Name(RoleName name);
	
	User findByEmail(String email);
	
}
