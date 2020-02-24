package com.example.nCentrala.model.dto;

public class BasicSearchQuery {

	private String field;
	
	private String value;
	
	private boolean phrase;

	public BasicSearchQuery(String field, String value, boolean phrase) {
		super();
		this.field = field;
		this.value = value;
		this.phrase = phrase;
	}

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

	public boolean isPhrase() {
		return phrase;
	}

	public void setPhrase(boolean phrase) {
		this.phrase = phrase;
	}
	
}
