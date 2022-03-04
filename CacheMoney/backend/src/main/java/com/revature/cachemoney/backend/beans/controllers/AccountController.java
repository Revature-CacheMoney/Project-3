package com.revature.cachemoney.backend.beans.controllers;

import static com.revature.cachemoney.backend.bones.AppUtils.*;


import com.revature.cachemoney.backend.beans.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.revature.cachemoney.backend.BackendApplication;
import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles HTTP requests for user accounts
 */

@RestController
@RequestMapping("/accounts")
public class AccountController {
	private final AccountsService accountsService;

	@Autowired
	public AccountController(AccountsService accountsService) {

		this.accountsService = accountsService;
	}

	// GET all accounts
	@GetMapping()
	public List<Account> getAllAccounts() {

		return accountsService.getAllAccounts();
	}

	// GET account by ID
	@GetMapping(value = "/{id}")
	public Optional<Account> getAccountByID(@PathVariable Integer id) {

		return accountsService.getAccountByID(id);
	}

	// POST an account
	@PostMapping()
	public void postAccount(@RequestBody Account account) {
		System.out.println("im here");
		accountsService.postAccount(account);
	}

	// DELETE an account by ID
	@DeleteMapping(value = "/{id}")
	public void deleteAccountById(@PathVariable Integer id) {

		accountsService.deleteAccountById(id);
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
