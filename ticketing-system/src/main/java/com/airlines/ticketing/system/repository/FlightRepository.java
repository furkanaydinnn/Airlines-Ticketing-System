package com.airlines.ticketing.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.airlines.ticketing.system.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	List<Flight> findByFromWhereAndToWhereIgnoreCase(@PathVariable("fromWhere") String from, @PathVariable("fromTo") String to);
	
	List<Flight> findByCompanyId(@RequestParam("company") Long companyId);

}
