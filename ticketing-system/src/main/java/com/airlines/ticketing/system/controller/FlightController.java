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

}
