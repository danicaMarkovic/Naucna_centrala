package com.example.nCentrala.model.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class LoginInfoDTO {

	
    
    private String username;

    private String password;
    
    public LoginInfoDTO() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
