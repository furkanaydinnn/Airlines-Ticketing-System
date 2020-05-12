package com.airlines.ticketing.system.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.airlines.ticketing.system.entity.Company;
import com.airlines.ticketing.system.exception.CompanyNotFoundException;
import com.airlines.ticketing.system.repository.CompanyRepository;

@RestController
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;
	
	@GetMapping("api/companies")
	public ResponseEntity<?> retrieveAllCompanies(){
		
		try {
			
			List<Company> companies = companyRepository.findAll();
			return ResponseEntity.ok(companies);
			
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	@GetMapping("api/companies/{id}")
	public ResponseEntity<?> retrieveCompanyById(@PathVariable long id) {

		try {

			Optional<Company> company = companyRepository.findById(id);

			if (!company.isPresent())
				throw new CompanyNotFoundException("id : " + id);

			return ResponseEntity.ok(company.get());
		} catch (CompanyNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("api/company")
	public ResponseEntity<?> retrieveCompanyByName(@RequestParam("name") String name) {

		try {

			Optional<Company> company = companyRepository.findByNameContainingIgnoreCase(name);

			if (!company.isPresent()) {
				throw new CompanyNotFoundException("name : " + name);
			}

			return ResponseEntity.ok(company.get());

		} catch (CompanyNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping("api/companies")
	public ResponseEntity<URI> createCompany(@RequestBody Company company) {

		try {

			Company savedCompany = companyRepository.save(company);
			Long id = savedCompany.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
