package com.revature.cachemoney.backend.beans.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.revature.cachemoney.backend.beans.repositories.AccountRepo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private Integer account_id;

	@Column(name = "type")
	private String type;

	@Column(name = "balance")
	private float balance;

	@Column(name= "account_name")
	private String account_name;

	@Column(name = "user_id")
	private String user_id;

	@Column(name = "transactions")
	@OneToMany
	private List<Transaction> transactions = new LinkedList<>();

	//@Column(name = "owner_id")
	//private int owner_id;
	//@Column(name = "owner")	
	//private User owner;

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	public void removeAccount(Transaction transaction) {
		transactions.remove(transaction);
	}
	
	public Account(String type) {
		this.type = type;
		this.balance = 0;
	}
}
