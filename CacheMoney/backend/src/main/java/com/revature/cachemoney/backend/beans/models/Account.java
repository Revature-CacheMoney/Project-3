package com.revature.cachemoney.backend.beans.models;

import java.sql.Array;
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

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "balance")
	private float balance;

	@Column(name = "name")
	private String name;

	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne
	private User userId;

	//private String[] accTypeName = new String[]{"checking", "savings"};

//	@Column(name = "transactions")
//	@OneToMany
//	private List<Transaction> transactions = new LinkedList<>();

//	public void addTransaction(Transaction trns) {
//		transactions.add(trns);
//	}
//
//	public void removeaddTransaction(Transaction trns) {
//		transactions.remove(trns);
//	}
//
//	public List<Transaction> getTransactions()
//	{
//		return this.transactions;
//	}
//
	public Account(String type) {
		this.type = type;
		this.name = this.type + hashCode();
		this.balance = 0;
	}

	public Account(String type, String name) {
		this.type = type;
		this.name = name;
		this.balance = 0;
	}
}
