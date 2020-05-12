package com.airlines.ticketing.system.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Flight Details", description="Contains all details of a flight")
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min=5, message="fromWhere should be at least 2 characters")
	@ApiModelProperty(notes = "fromWhere should have at least 5 characters")
	private String fromWhere;

	@Size(min=5, message="fromTo should be at least 2 characters")
	@ApiModelProperty(notes = "fromTo should have at least 5 characters")
	private String toWhere;

	@Positive
	@ApiModelProperty(notes = "passengerCapacity should be bigger then zero")
	private Integer passengerCapacity;

	@ManyToOne(fetch = FetchType.LAZY)
	// @JsonIgnore
	private Company company;

	@OneToMany(mappedBy = "flight")
	private List<Ticket> tickets;

	public Flight() {
		super();

	}

	public Flight(Long id, String fromWhere, String toWhere, Integer passengerCapacity, Company company) {
		super();
		this.id = id;
		this.fromWhere = fromWhere;
		this.toWhere = toWhere;
		this.passengerCapacity = passengerCapacity;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromWhere() {
		return fromWhere;
	}

	public void setFromWhere(String fromWhere) {
		this.fromWhere = fromWhere;
	}

	public String getToWhere() {
		return toWhere;
	}

	public void setToWhere(String toWhere) {
		this.toWhere = toWhere;
	}

	public Integer getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(Integer passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	@JsonIgnore
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}
