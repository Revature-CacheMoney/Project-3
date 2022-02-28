package com.revature.cachemoney.backend.beans.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	private final AccountRepo accountRepository;

	/**
	 * Retrieve access to the ItineraryRepository from Spring.
	 * 
	 * @param itineraryRepository
	 */
	@Autowired
	public AccountController(AccountRepo accountRepository) {
		this.accountRepository = accountRepository;
	}

	/**
	 * GET all Itineraries.
	 * 
	 * @return List of all existing Itineraries
	 */
	@GetMapping()
	public List<Account> getItinerary() {
		return accountRepository.findAll();
	}

	/**
	 * GET an Itinerary by id.
	 * 
	 * @param id
	 * @return the Itinerary based on id
	 */
	@GetMapping(value = "/{id}")
	public Optional<Account> getItineraryByID(@PathVariable Integer id) {
		return accountRepository.findById(id);
	}

	/**
	 * POST an Itinerary.
	 * 
	 * @param itinerary
	 */
	@PostMapping()
	public void postItinerary(@RequestBody Account itinerary) {
		accountRepository.save(itinerary);
	}

	/**
	 * DELETE an Itinerary by id.
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	public void deleteItineraryById(@PathVariable Integer id) {
		accountRepository.deleteById(id);
	}
}
