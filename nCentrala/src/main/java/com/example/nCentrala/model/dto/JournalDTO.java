package com.example.nCentrala.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.example.nCentrala.model.Editor;
import com.example.nCentrala.model.Journal;
import com.example.nCentrala.model.PaymentMethod;
import com.example.nCentrala.model.ScienceArea;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class JournalDTO {

	private Long id;
	
	private String name;
	
	private String issn;
	
	private boolean isOpenAccess;
	
	private boolean isActivated;
	
	private List<ScienceArea> scienceAreas =  new ArrayList<ScienceArea>();
	
	private List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>(); 
	
	private Editor mainEditor;
	
	private List<EditorDTO> editors = new ArrayList<EditorDTO>();
	
	public JournalDTO() {}
	
	public JournalDTO(Journal j) {
		super();
		this.id = j.getId();
		this.name = j.getName();
		this.issn = j.getIssn();
		this.isOpenAccess = j.isOpenAccess();
		this.isActivated = j.isActivated();
		this.scienceAreas = j.getScienceAreas();
		this.paymentMethods = j.getPaymentMethods();
		this.mainEditor = j.getMainEditor();
		
		for(Editor e : j.getEditors())
		{
			this.editors.add(new EditorDTO(e));
		}
		
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public boolean isOpenAccess() {
		return isOpenAccess;
	}

	public void setOpenAccess(boolean isOpenAccess) {
		this.isOpenAccess = isOpenAccess;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public List<ScienceArea> getScienceAreas() {
		return scienceAreas;
	}

	public void setScienceAreas(List<ScienceArea> scienceAreas) {
		this.scienceAreas = scienceAreas;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Editor getMainEditor() {
		return mainEditor;
	}

	public void setMainEditor(Editor mainEditor) {
		this.mainEditor = mainEditor;
	}

	public List<EditorDTO> getEditors() {
		return editors;
	}

	public void setEditors(List<EditorDTO> editors) {
		this.editors = editors;
	}
	
	
}
