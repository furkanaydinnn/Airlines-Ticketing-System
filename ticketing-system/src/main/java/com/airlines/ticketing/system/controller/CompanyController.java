package com.airlines.ticketing.system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airlines.ticketing.system.entity.Company;
import com.airlines.ticketing.system.exception.CompanyNotFoundException;
import com.airlines.ticketing.system.repository.CompanyRepository;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@GetMapping("api/companies/{id}")
	public ResponseEntity<?> retrieveCompanyById(@PathVariable long id) {
		
		try {
			
			Optional<Company> company =  companyRepository.findById(id);
			
			if(!company.isPresent())
				throw new CompanyNotFoundException("id : " + id);
			
			return ResponseEntity.ok(company.get());
		}
		catch(CompanyNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GetMapping("api/companies")
	public ResponseEntity<?> retrieveCompanyByName(@RequestParam("name") String name) {
		
		try {
			
			Optional<Company> company =  companyRepository.findByName(name);
			
			if(!company.isPresent()) {
				throw new CompanyNotFoundException("name : " + name);
			}
			
			return ResponseEntity.ok(company.get());
			
		}
		catch(CompanyNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
			
	}

}
