package com.airlines.ticketing.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlines.ticketing.system.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
