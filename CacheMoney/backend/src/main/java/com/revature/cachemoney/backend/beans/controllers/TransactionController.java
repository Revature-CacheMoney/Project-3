package com.revature.cachemoney.backend.beans.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cachemoney.backend.beans.models.Transaction;
import com.revature.cachemoney.backend.beans.repositories.TransactionRepo;

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
	}
}
