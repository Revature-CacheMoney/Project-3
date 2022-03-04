package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;
import java.util.Optional;

import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private final TransactionService transactionService;

	/**
	 * Retrieve access to the TransactionRepo from Spring.
	 * 
	 * @param transactionService
	 */
	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/**
	 * GET all Transactions.
	 * 
	 * @return List of all existing Transactions
	 */
	@GetMapping()
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();
	}

	/**
	 * GET an Transaction by id.
	 * 
	 * @param id
	 * @return the Transaction based on id
	 */
	@GetMapping(value = "/{id}")
	public Optional<Transaction> getTransactionByID(@PathVariable Integer id) {
		return transactionService.getTransactionById(id);
	}

	/**
	 * POST an Transaction.
	 * 
	 * @param transaction
	 */
	@PostMapping()
	public void postTransaction(@RequestBody Transaction transaction) {
		transactionService.postTransaction(transaction);
	}

	/**
	 * DELETE an Transaction by id.
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	public void deleteTransactionById(@PathVariable Integer id) {
		transactionService.deleteTransactionById(id);
	}

}
