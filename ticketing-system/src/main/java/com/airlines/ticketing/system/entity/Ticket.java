package com.airlines.ticketing.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Ticket Details", description="Contains all details of a ticket")
@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketNumber;

	@Size(min=2, message="company should be at least 2 characters")
	@ApiModelProperty(notes = "company should have at least 5 characters")
	private String company;

	@Positive
	@ApiModelProperty(notes = "gateNumber should be bigger then zero")
	private Integer gateNumber;

	@Positive
	@ApiModelProperty(notes = "seatNumber should be bigger then zero")
	private String seatNumber;

	@FutureOrPresent
	@ApiModelProperty(notes = "boardingTime should be present or future")
	private Date boardingTime;

	@Positive
	@ApiModelProperty(notes = "price should be bigger then zero")
	private Float price;

	@Size(min=16, message="cardNumber should be at least 16 characters")
	private String cardNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	private Flight flight;

	public Ticket() {
		super();
	}

	public Ticket(Long ticketNumber, String company, Integer gateNumber, String seatNumber, Date boardingTime,
			Float price, String cardNumber, Flight flight) {
		super();
		this.ticketNumber = ticketNumber;
		this.company = company;
		this.gateNumber = gateNumber;
		this.seatNumber = seatNumber;
		this.boardingTime = boardingTime;
		this.price = price;
		this.cardNumber = cardNumber;
		this.flight = flight;
	}

	public Long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(Long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getGateNumber() {
		return gateNumber;
	}

	public void setGateNumber(Integer gateNumber) {
		this.gateNumber = gateNumber;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Date getBoardingTime() {
		return boardingTime;
	}

	public void setBoardingTime(Date boardingTime) {
		this.boardingTime = boardingTime;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@JsonIgnore
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
