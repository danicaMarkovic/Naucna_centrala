package com.example.nCentrala.model.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.mapping.Array;

public class FormSubmissionDTO implements Serializable{

	private String fieldId;
	
	private String fieldValue;
	
	private ArrayList<String> areas = new ArrayList<String>();
	
	private ArrayList<String> methods = new ArrayList<String>();
	
	public FormSubmissionDTO() {}

	public FormSubmissionDTO(String fieldId, String fieldValue, ArrayList<String> areas, ArrayList<String> methods) {
		super();
		this.fieldId = fieldId;
		this.fieldValue = fieldValue;
		this.areas = areas;
		this.methods = methods;
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

	public ArrayList<String> getAreas() {
		return areas;
	}

	public void setAreas(ArrayList<String> areas) {
		this.areas = areas;
	}

	public ArrayList<String> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<String> methods) {
		this.methods = methods;
	}
	
}
