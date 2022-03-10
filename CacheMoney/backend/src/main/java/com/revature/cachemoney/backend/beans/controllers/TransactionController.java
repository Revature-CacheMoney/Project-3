package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle requests related to Transactions.
 * 
 * @author Brian Gardner, Cody Gonsowski, & Jeffrey Lor
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private final TransactionService transactionService;
	private final ObjectMapper mapper;

	@Autowired
	public TransactionController(TransactionService transactionService, ObjectMapper mapper) {
		this.transactionService = transactionService;
		this.mapper = mapper;
	}

	/**
	 * GET *ALL* Transactions.
	 * 
	 * @return List of all Transactions
	 */
	@GetMapping(value = "/all")
	@RequireJwt
	public List<Transaction> getAllTransactions() {
		return transactionService.getAllTransactions();
	}

	/**
	 * GET the Transaction with provided ID of the associated User.
	 * Returns a bad request if the Transaction is not associated with the User.
	 * 
	 * @param token         for current session
	 * @param userId        for current User
	 * @param transactionId for User's Transaction
	 * @return Transaction associated with the User
	 * @throws JsonProcessingException
	 */
	@GetMapping
	@RequireJwt
	public ResponseEntity<String> getTransactionByID(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@RequestBody Integer transactionId)
			throws JsonProcessingException {

		return ResponseEntity.ok()
				.body(mapper.writeValueAsString(transactionService.getTransactionById(transactionId, userId)));
	}

	/**
	 * POST a Transaction with provided ID.
	 * Returns a bad request if the POST is unsuccessful.
	 * 
	 * @param token       for current session
	 * @param userId      for current User
	 * @param transaction for User's Transaction
	 * @return OK | Bad Request based on POST success
	 */
	@PostMapping
	@RequireJwt
	public ResponseEntity<String> postTransaction(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@RequestBody Transaction transaction) {

		if (transactionService.postTransaction(transaction, userId)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.badRequest().build();
	}

	/**
	 * DELETE a Transaction with provided ID.
	 * Returns a bad request if the DELETE is unsuccessful.
	 * 
	 * @param token         for current session
	 * @param userId        for current User
	 * @param transactionId for User's Transaction
	 * @return OK | Bad Request based on DELETE success
	 */
	@DeleteMapping
	@RequireJwt
	public ResponseEntity<String> deleteTransactionById(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@PathVariable Integer transactionId) {

		if (transactionService.deleteTransactionById(transactionId, userId)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.badRequest().build();
	}

}
