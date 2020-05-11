package com.airlines.ticketing.system.exception;

public class CompanyNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CompanyNotFoundException(String message) {
		super(message);
		
	}

}
