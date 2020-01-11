package com.example.nCentrala.model;

import java.io.Serializable;
import java.util.HashSet;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Journal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String issn;
	
	@Column(nullable = false)
	private boolean isOpenAccess;
	
	@Column(nullable = false)
	private boolean isActivated;
	
	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<ScienceArea> scienceAreas =  new HashSet<ScienceArea>();
	
	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<PaymentMethod> paymentMethods = new HashSet<PaymentMethod>(); 
	
	public Journal() {}
	
	public Journal(Long id, String name, String issn, boolean isOpenAccess, boolean isActivated,
			Set<ScienceArea> scienceAreas, Set<PaymentMethod> paymentMethods) {
		super();
		this.id = id;
		this.name = name;
		this.issn = issn;
		this.isOpenAccess = isOpenAccess;
		this.isActivated = isActivated;
		this.scienceAreas = scienceAreas;
		this.paymentMethods = paymentMethods;
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

	public boolean isOpenAccess() {
		return isOpenAccess;
	}

	public void setOpenAccess(boolean isOpenAccess) {
		this.isOpenAccess = isOpenAccess;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public Set<ScienceArea> getScienceAreas() {
		return scienceAreas;
	}

	public void setScienceAreas(Set<ScienceArea> scienceAreas) {
		this.scienceAreas = scienceAreas;
	}

	public Set<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	
}
