package com.example.nCentrala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nCentrala.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
