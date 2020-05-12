package com.airlines.ticketing.system.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Company Details", description="Contains all details of a company")
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min=2, message="Name should be at least 2 characters")
	@ApiModelProperty(notes = "Name should have at least 2 characters")
	private String name;

	@OneToMany(mappedBy = "company")
	private List<Flight> flightList;

	public Company() {
		super();

	}

	public Company(Long id, String name, List<Flight> flightList) {
		super();
		this.id = id;
		this.name = name;
		this.flightList = flightList;
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

	
	public List<Flight> getFlightList() {
		return flightList;
	}

	//@JsonIgnore
	public void setFlightList(List<Flight> flightList) {
		this.flightList = flightList;
	}

}
