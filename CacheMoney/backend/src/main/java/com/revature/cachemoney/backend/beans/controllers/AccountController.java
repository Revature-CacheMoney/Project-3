package com.revature.cachemoney.backend.beans.controllers;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cachemoney.backend.beans.annotations.RequireJwt;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.services.AccountsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle requests related to Accounts.
 * 
 * @author Brian Gardner, Cody Gonsowski, & Jeffrey Lor
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
	private final AccountsService accountsService;
	private final ObjectMapper mapper;

	@Autowired
	public AccountController(AccountsService accountsService, ObjectMapper mapper) {
		this.accountsService = accountsService;
		this.mapper = mapper;
	}

	// GET all accounts
	@GetMapping(value = "/all")
	public List<Account> getAllAccounts() {
		return accountsService.getAllAccounts();
	}

	/**
	 * GET the Account with provided ID of the associated User.
	 * Returns a bad request if the Account is not associated with the User.
	 * 
	 * @param token     for current session
	 * @param userId    for current user
	 * @param accountId for user's account
	 * @return Account associated with the User
	 * @throws JsonProcessingException
	 */
	@GetMapping
	@RequireJwt
	public ResponseEntity<String> getAccountByID(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@RequestBody Integer accountId)
			throws JsonProcessingException {

		// retrieve account
		Optional<Account> account = accountsService.getAccountByID(accountId, userId);

		// see if an account was actually retrieved
		if (account.isPresent()) {
			return ResponseEntity.ok().body(mapper.writeValueAsString(account.get()));
		}

		// return bad request when account is not retrieved successfully
		return ResponseEntity.badRequest().build();
	}

	/**
	 * POST an Account with provided ID.
	 * Returns a bad request if the POST is unsuccessful.
	 * 
	 * @param token     for current session
	 * @param userId    for current user
	 * @param accountId for user's account
	 * @return OK | Bad Request based on POST success
	 * @throws JsonProcessingException
	 */
	@PostMapping
	@RequireJwt
	public ResponseEntity<String> postAccount(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@RequestBody Account account)
			throws JsonProcessingException {

		accountsService.postAccount(account);
		return ResponseEntity.ok().build();
	}

	// DELETE an account by ID
	@DeleteMapping
	@RequireJwt
	public ResponseEntity<String> deleteAccountById(
			@RequestHeader(name = "token") String token,
			@RequestHeader(name = "userId") Integer userId,
			@RequestBody Integer accountId) {

		accountsService.deleteAccountById(accountId);
		return ResponseEntity.ok().build();
	}

	// GET transaction by ID
	@GetMapping(value = "/transactions/{id}")
	public List<Transaction> getTransactionsById(@PathVariable Integer id) {
		return accountsService.getTransactionsById(id);
	}
}
