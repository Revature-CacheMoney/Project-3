package com.revature.cachemoney.backend.beans.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import lombok.*;

import com.revature.cachemoney.backend.beans.repositories.AccountRepo;



/**
 * Object for interacting with Users.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	@Column(name = "type")
	private String type;

	@Column(name = "balance")
	private Double balance;

	// @Column(name = "owner_id")
	// private int owner_id;
	// @Column(name = "owner")
	// private User owner;

	@Column(name = "transactions")
	@OneToMany
	private List<Transaction> transactions = new LinkedList<>();

	public void addTransaction(Transaction trns) {
		transactions.add(trns);
	}

	public void removeaddTransaction(Transaction trns) {
		transactions.remove(trns);
	}
	
	public List<Transaction> getTransactions()
	{
		return this.transactions;
	}
	
	public Account(String type) {
		this.type = type;
		this.balance = 0.00;
	}
}
