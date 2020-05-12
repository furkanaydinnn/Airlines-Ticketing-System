package com.airlines.ticketing.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.airlines.ticketing.system.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
	
	Optional<Airport> findByNameContainingIgnoreCase(@RequestParam("name") String name);

}
