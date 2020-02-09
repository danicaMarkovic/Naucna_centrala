package com.example.nCentrala.model.dto;

import com.example.nCentrala.model.Editor;

public class EditorDTO {

	private String name;
	
	private String username;
	
	private String email;
	
	public EditorDTO(String name, String username, String email) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
	}
	
	public EditorDTO(Editor e)
	{
		this.name = e.getName();
		this.username = e.getUsername();
		this.email = e.getEmail();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
