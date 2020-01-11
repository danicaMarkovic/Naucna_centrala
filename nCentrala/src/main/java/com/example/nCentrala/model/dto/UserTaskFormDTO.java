package com.example.nCentrala.model.dto;

import java.io.Serializable;
import java.util.List;

public class UserTaskFormDTO implements Serializable{

	private String taskId;
	
	private String processId;
	
	private List<FormSubmissionDTO> formFields;
	
	public UserTaskFormDTO() {}

	public UserTaskFormDTO(String taskId, String processId, List<FormSubmissionDTO> formFields) {
		super();
		this.taskId = taskId;
		this.processId = processId;
		this.formFields = formFields;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public List<FormSubmissionDTO> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormSubmissionDTO> formFields) {
		this.formFields = formFields;
	}
	
}
