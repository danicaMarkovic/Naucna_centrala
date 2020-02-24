package com.example.nCentrala.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.nCentrala.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="discriminator",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="U")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;

	@Column(nullable = false, length = 60)
	private String email;
	
	@Column(nullable = false, length = 60)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private boolean isActivated;
	
	@Column(nullable = false)
	private double longit;
	
	@Column(nullable = false)
	private double lat;
	
	@Column
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<ScienceArea> areasOfInterest =  new HashSet<ScienceArea>();
	
	@Column
	@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Role> roles = new HashSet<Role>();
	
	
	public User() {
		
	}
	
	public User(String name, String surname, String email, String username, String password, String city,
			String state, boolean isActivated, Set<ScienceArea> areasOfInterest, Set<Role> roles) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.city = city;
		this.state = state;
		this.isActivated = isActivated;
		this.areasOfInterest = areasOfInterest;
		this.roles = roles;
	}


	public User(UserDTO user)
	{
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.state = user.getState();
		this.password = user.getPassword();
		this.state = user.getState();
		this.city = user.getCity();
		this.username = user.getUsername();
		this.areasOfInterest = user.getAreas();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isActivated() {
		return isActivated;
	}


	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}


	public Set<ScienceArea> getAreasOfInterest() {
		return areasOfInterest;
	}

	public void setAreasOfInterest(Set<ScienceArea> areasOfInterest) {
		this.areasOfInterest = areasOfInterest;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public double getLongit() {
		return longit;
	}

	public void setLongit(double longit) {
		this.longit = longit;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
	
}
