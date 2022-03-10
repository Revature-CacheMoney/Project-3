package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.security.JwtUtil;
import com.revature.cachemoney.backend.beans.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private final TransactionService transactionService;
	private final JwtUtil jwtUtil;
	private final ObjectMapper mapper;

	/**
	 * Retrieve access to the TransactionRepo from Spring.
	 *
	 * @param transactionService
	 * @param jwtUtil
	 * @param mapper
	 */
	@Autowired
	public TransactionController(TransactionService transactionService, JwtUtil jwtUtil, ObjectMapper mapper) {
		this.transactionService = transactionService;
		this.jwtUtil = jwtUtil;
		this.mapper = mapper;
	}

	/**
	 * GET all Transactions.
	 * 
	 * @return List of all existing Transactions
	 */
	@GetMapping()
	@RequireJwt
	public ResponseEntity<String> getAllTransactions(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId)
			throws JsonProcessingException {
		return ResponseEntity.ok().body(mapper.writeValueAsString(transactionService.getAllTransactions()));
	}

	/**
	 * GET an Transaction by id.
	 * 
	 * @param id
	 * @return the Transaction based on id
	 */
	@GetMapping(value = "/{id}")
	@RequireJwt
	public ResponseEntity<String> getTransactionByID(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@PathVariable Integer id)
			throws JsonProcessingException {
		return ResponseEntity.ok().body(mapper.writeValueAsString(transactionService.getTransactionById(id)));
	}

	/**
	 * POST an Transaction.
	 * 
	 * @param transaction
	 */
	@PostMapping()
	@RequireJwt
	public void postTransaction(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@RequestBody Transaction transaction) {
		transactionService.postTransaction(transaction);
	}

	/**
	 * DELETE an Transaction by id.
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	@RequireJwt
	public void deleteTransactionById(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@PathVariable Integer id) {
		transactionService.deleteTransactionById(id);
	}

}
