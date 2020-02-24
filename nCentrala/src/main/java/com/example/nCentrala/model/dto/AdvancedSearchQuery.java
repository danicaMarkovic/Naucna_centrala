package com.example.nCentrala.model.dto;

public class AdvancedSearchQuery {

	private String field;
	private String value;
	private String operator;
	private boolean phrase;
	
	public AdvancedSearchQuery() {}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean isPhrase() {
		return phrase;
	}

	public void setPhrase(boolean phrase) {
		this.phrase = phrase;
	}
	
}
