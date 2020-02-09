package com.example.nCentrala.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Journal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, length = 60)
	@NaturalId
	private String issn;
	
	@Column(nullable = false)
	private boolean isOpenAccess;
	
	@Column(nullable = false)
	private boolean isActivated;
	
	@Column(nullable = false)
	private double price;
	
	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
	private List<ScienceArea> scienceAreas =  new ArrayList<ScienceArea>();
	
	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
	private List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>(); 
	
	@OneToOne
	private Editor mainEditor;
	
	@OneToMany(mappedBy="journal",fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Editor> editors = new ArrayList<Editor>();
	
	@OneToMany(mappedBy="journal",fetch = FetchType.LAZY)
	private List<Subscription> subscriptions = new ArrayList<Subscription>();
	
	public Journal() {}
	

	public Journal(Long id, String name, String issn, boolean isOpenAccess, boolean isActivated, double price,
			List<ScienceArea> scienceAreas, List<PaymentMethod> paymentMethods, Editor mainEditor,
			List<Editor> editors) {
		super();
		this.id = id;
		this.name = name;
		this.issn = issn;
		this.isOpenAccess = isOpenAccess;
		this.isActivated = isActivated;
		this.price = price;
		this.scienceAreas = scienceAreas;
		this.paymentMethods = paymentMethods;
		this.mainEditor = mainEditor;
		this.editors = editors;
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

	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}


	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
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


	public List<Editor> getEditors() {
		return editors;
	}


	public void setEditors(List<Editor> editors) {
		this.editors = editors;
	}
	
}
