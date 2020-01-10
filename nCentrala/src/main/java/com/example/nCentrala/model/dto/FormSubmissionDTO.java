package com.example.nCentrala.model.dto;

import java.io.Serializable;

public class FormSubmissionDTO implements Serializable{

	private String fieldId;
	
	private String fieldValue;
	
	public FormSubmissionDTO() {}

	public FormSubmissionDTO(String fieldId, String fieldValue) {
		super();
		this.fieldId = fieldId;
		this.fieldValue = fieldValue;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
}
