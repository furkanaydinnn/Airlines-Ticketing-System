package com.airlines.ticketing.system.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.airlines.ticketing.system.entity.Ticket;
import com.airlines.ticketing.system.exception.TicketNotFoundException;
import com.airlines.ticketing.system.repository.TicketRepository;

@RestController
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping("api/tickets")
	public ResponseEntity<?> retrieveAllTickets() {

		try {

			List<Ticket> tickets = ticketRepository.findAll();
			return ResponseEntity.ok(tickets);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("api/tickets/{id}")
	public ResponseEntity<?> retrieveTicketByTicketNumber(@PathVariable Long ticketNumber) {

		try {

			Optional<Ticket> ticket = ticketRepository.findById(ticketNumber);

			if (!ticket.isPresent()) {
				throw new TicketNotFoundException("ticketNumber : " + ticketNumber);
			}

			return ResponseEntity.ok(ticket);

		} catch (TicketNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("api/tickets/{id}")
	public ResponseEntity<?> cancelTicket(@PathVariable Long ticketNumber) {

		try {

			ticketRepository.deleteById(ticketNumber);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@PostMapping("api/tickets")
	public ResponseEntity<URI> createTicket(@RequestBody Ticket ticket){
		
		try {
			
			String cardNumber = ticket.getCardNumber();
			cardNumber = cardNumber.replaceAll("[-*, ]", "");
			
			String maskedCardNumber = maskCardNumber(cardNumber, "##xx-xxxx-xxxx-xx##");
			ticket.setCardNumber(maskedCardNumber);
			
			Ticket createdTicket = ticketRepository.save(ticket);
			Long id = createdTicket.getTicketNumber();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();

		}
		catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	private static String maskCardNumber(String cardNumber, String mask) {

	    // format the number
	    int index = 0;
	    StringBuilder maskedNumber = new StringBuilder();
	    for (int i = 0; i < mask.length(); i++) {
	        char c = mask.charAt(i);
	        if (c == '#') {
	            maskedNumber.append(cardNumber.charAt(index));
	            index++;
	        } else if (c == 'x') {
	            maskedNumber.append(c);
	            index++;
	        } else {
	            maskedNumber.append(c);
	        }
	    }

	    // return the masked number
	    return maskedNumber.toString();
	}

}
