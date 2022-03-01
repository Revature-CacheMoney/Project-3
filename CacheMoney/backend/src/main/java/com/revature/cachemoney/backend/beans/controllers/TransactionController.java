package com.revature.cachemoney.backend.beans.controllers;

<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes

import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

<<<<<<< Updated upstream
@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	TransactionRepo trnsRepository;
	
	@PostMapping()
	public String postUser(@RequestBody Transaction user) {
		try {
			trnsRepository.save(user);
		} catch (Exception e) {
			System.out.println("User cannot be registered.");

			// inform failed result
			return "User cannot be registered. The email you entered (" + /*user.getEmail()+*/  ") may already be in use.";
		}

		// inform successful result
		return "User " + /*user.getFirstName() + user.getLastName() + " (ID = " + user.getUser_id()
				+*/ ") registered successfully!";
=======
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
	 * @param transaction
	 */
	@PostMapping()
	public void postTransaction(@RequestBody Transaction trns) {
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
>>>>>>> Stashed changes
	}
}
