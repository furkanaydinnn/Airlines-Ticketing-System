package com.airlines.ticketing.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketNumber;

	private String company;

	private Integer gateNumber;

	private String seatNumber;

	private Date boardingTime;

	private Float price;

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
