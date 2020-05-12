package com.airlines.ticketing.system.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.airlines.ticketing.system.entity.Flight;
import com.airlines.ticketing.system.exception.FlightNotFoundException;
import com.airlines.ticketing.system.repository.FlightRepository;

@RestController
public class FlightController {

	@Autowired
	private FlightRepository flightRepository;

	@GetMapping("api/flights")
	public ResponseEntity<?> retrieveAllFlights() {

		try {

			List<Flight> flights = flightRepository.findAll();
			return ResponseEntity.ok(flights);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@GetMapping("api/flights/{id}")
	public ResponseEntity<?> retrieveFlight(@PathVariable Long id) {

		try {

			Optional<Flight> flight = flightRepository.findById(id);

			if (!flight.isPresent()) {
				throw new FlightNotFoundException("id : " + id);
			}

			return ResponseEntity.ok(flight);
		} catch (FlightNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@GetMapping("api/flights/from/{from}/to/{to}")
	public ResponseEntity<?> retrieveFlightsByFromAndTo(@PathVariable String from, @PathVariable String to){
		
		try {
			
			List<Flight> flights = flightRepository.findByFromWhereAndToWhereIgnoreCase(from, to);
			
			if(flights.isEmpty()) {
				throw new FlightNotFoundException("fromWhere : " + from + "-" + "fromTo : " + to);
			}
			
			return ResponseEntity.ok(flights);	
		}
		catch(FlightNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("api/flights/byCompany")
	public ResponseEntity<?> retrieveFlightsByCompany(@RequestParam("companyId") Long companyId){
		
		try {
			
			List<Flight> flights = flightRepository.findByCompanyId(companyId);
			
			if(flights.isEmpty()) {
				throw new FlightNotFoundException("companyId : " + companyId);
			}
			
			return ResponseEntity.ok(flights);
		}
		catch(FlightNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	@PostMapping("api/flights")
	public ResponseEntity<URI> createFlight(@RequestBody Flight flight) {

		try {

			Flight savedFlight = flightRepository.save(flight);
			Long id = savedFlight.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@PutMapping("api/flights/{id}")
	public ResponseEntity<?> updateFlight(@RequestBody Flight flight, @PathVariable("id") Long id) {
		
		try {
			
			Optional<Flight> tempFlight = flightRepository.findById(id);
			if(!tempFlight.isPresent()) {
				throw new FlightNotFoundException("id : " + id);
			}
			
			Flight currentFlight = tempFlight.get();
			currentFlight.setPassengerCapacity(flight.getPassengerCapacity());
			flightRepository.save(currentFlight);
			// here, price will be updated later..
			return ResponseEntity.ok(currentFlight);
		}
		catch(FlightNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
		catch(Exception ex ) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

}
