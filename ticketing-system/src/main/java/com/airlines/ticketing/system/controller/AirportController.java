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

import com.airlines.ticketing.system.entity.Airport;
import com.airlines.ticketing.system.exception.AirportNotFoundException;
import com.airlines.ticketing.system.repository.AirportRepository;

@RestController
public class AirportController {
	
	@Autowired
	private AirportRepository airportRepository;
	
	@GetMapping("api/airports")
	public ResponseEntity<?> retrieveAllAirports(){
		
		try {
			
			List<Airport> airports = airportRepository.findAll();
			return ResponseEntity.ok(airports);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("api/airports/{id}")
	public ResponseEntity<?> retrieveAirportById(@PathVariable Long id){
		
		try {
			
			Optional<Airport> airport = airportRepository.findById(id);
			
			if(!airport.isPresent()) {
				throw new AirportNotFoundException("id : " + id);
			}
			
			return ResponseEntity.ok(airport);
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GetMapping("api/airportByName")
	public ResponseEntity<?> retrieveAirportByName(@RequestParam("name") String name){
		
		try {
			
			List<Airport> airports =  airportRepository.findByNameContainingIgnoreCase(name);
			if(airports.isEmpty()) {
				throw new AirportNotFoundException("name : " + name);
			}
			
			return ResponseEntity.ok(airports);
		}
		catch(AirportNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}
	
	@PostMapping("api/airports")
	public ResponseEntity<URI> createAirport(@RequestBody Airport airport){
		
		try {
			
			Airport savedAirport = airportRepository.save(airport);
			Long id = savedAirport.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	

}
