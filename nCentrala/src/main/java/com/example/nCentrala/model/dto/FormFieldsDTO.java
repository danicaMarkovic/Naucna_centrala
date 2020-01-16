package com.example.nCentrala.model.dto;

import java.util.List;

import org.camunda.bpm.engine.form.FormField;

public class FormFieldsDTO {
	
	private String taskName;
	
	private String taskId;
	
	private List<FormField> formFields;
	
	private String processInstanceId;
	
	public FormFieldsDTO() {}

	public FormFieldsDTO(String taskName, String taskId, List<FormField> formFields, String processInstanceId) {
		super();
		this.taskName = taskName;
		this.taskId = taskId;
		this.formFields = formFields;
		this.processInstanceId = processInstanceId;
	}
	

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<FormField> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormField> formFields) {
		this.formFields = formFields;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
}
