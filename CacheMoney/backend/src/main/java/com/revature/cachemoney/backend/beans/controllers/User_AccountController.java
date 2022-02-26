package com.revature.cachemoney.backend.beans.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cachemoney.backend.beans.models.Account;
import com.revature.cachemoney.backend.beans.models.User_Account;
import com.revature.cachemoney.backend.beans.repositories.User_AccountRepo;

@RestController
@RequestMapping("/users/accounts")
public class User_AccountController {
	private final User_AccountRepo userAcctRepository;

	@Autowired
	public User_AccountController(User_AccountRepo repo) {
		this.userAcctRepository = repo;
	}

	// GET itinerary
	@RequestMapping(method = RequestMethod.GET)
	public List<User_Account> getUserItineraries() {
		
		return userAcctRepository.findAll();
	}

	// GET itinerary by Id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<Account> getItineraryByID(@PathVariable Integer id) {
		// return userItineraryRepository.findById(id);
		System.out.println("");
		System.out.println("8675309");
		ArrayList<User_Account> U = (ArrayList<User_Account>) userAcctRepository.findAll();
		ArrayList<Account> I = new ArrayList<Account>();
		// log(U.size());
		for (int i = 0; i < U.size(); i++) {
			if (U.get(i).getUserID() == id)
				I.add(U.get(i).getAccount());
		}
		return I;
	}


}
