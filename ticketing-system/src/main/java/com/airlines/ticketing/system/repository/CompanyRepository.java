package com.airlines.ticketing.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.airlines.ticketing.system.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	List<Company> findByNameContainingIgnoreCase(@RequestParam("name") String name);

}
