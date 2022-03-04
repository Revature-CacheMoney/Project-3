package com.revature.cachemoney.backend.beans.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private final TransactionRepo transactionRepository;

	/**
	 * Retrieve access to the TransactionRepo from Spring.
	 * 
	 * @param transactionRepository
	 */
	@Autowired
	public TransactionController(TransactionRepo transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	/**
	 * GET all Transactions.
	 * 
	 * @return List of all existing Transactions
	 */
	@GetMapping()
	public List<Transaction> getTransaction() {
		return transactionRepository.findAll();
	}

	/**
	 * GET an Transaction by id.
	 * 
	 * @param id
	 * @return the Transaction based on id
	 */
	@GetMapping(value = "/{id}")
	public Optional<Transaction> getTransactionByID(@PathVariable Integer id) {
		return transactionRepository.findById(id);
	}

	/**
	 * POST an Transaction.
	 * 
	 * @param trns
	 */
	@PostMapping()
	public void postTransaction(@RequestBody Transaction trns) {
		System.out.println("i am here this time");
//		trns.setTransactionDate(new Date());
		transactionRepository.save(trns);
	}

	/**
	 * DELETE an Transaction by id.
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	public void deleteTransactionById(@PathVariable Integer id) {
		transactionRepository.deleteById(id);
	}
	

	
}
