package com.revature.cachemoney.backend.beans.controllers;

import static com.revature.cachemoney.backend.bones.AppUtils.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.cachemoney.backend.BackendApplication;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.AccountRepo;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

import java.util.ArrayList;
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
	public List<Account> getAccount() {
		return accountRepository.findAll();
	}

	/**
	 * GET an Itinerary by id.
	 * 
	 * @param id
	 * @return the Itinerary based on id
	 */
	@GetMapping(value = "/{id}")
	public Optional<Account> getAccountByID(@PathVariable Integer id) {
		return accountRepository.findById(id);
	}

	/**
	 * POST an Itinerary.
	 * 
	 * @param itinerary
	 */
	@PostMapping()
	public void postAccount(@RequestBody Account itinerary) {
		accountRepository.save(itinerary);
	}

	/**
	 * DELETE an Itinerary by id.
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	public void deleteAccountById(@PathVariable Integer id) {
		accountRepository.deleteById(id);
	}
	
    @GetMapping(value = "/{id}/transactions")
    public List<Transaction> getTransactionsById(@PathVariable Integer id)
    {
    	
    	TransactionRepo trns = BackendApplication.trnsRepository;
    	ArrayList<Transaction> res = (ArrayList<Transaction>) trns.findByAccountId(id);
    	Log(">");
    	return res;
    }
	
	
}
