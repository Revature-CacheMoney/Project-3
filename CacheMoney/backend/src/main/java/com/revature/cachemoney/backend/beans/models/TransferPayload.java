package com.revature.cachemoney.backend.beans.models;

import javax.persistence.Entity;

import com.revature.cachemoney.backend.beans.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class TransferPayload {
	private final AccountService accountService;

	public int fromId = 0; // accountId
	public int toId = 0;
	// public Account source;
	// public Account target;
	public double amount;

	@Autowired
	public TransferPayload(int fromId, int toId, double amount, AccountService accountService) {
		this.fromId = fromId;
		this.toId = toId;
		// this.source = BackendApplication.accountService.getAccountByID(fromId).get();
		// this.target = BackendApplication.accountService.getAccountByID(toId).get();
		this.amount = amount;
		this.accountService = accountService;
	}
}
