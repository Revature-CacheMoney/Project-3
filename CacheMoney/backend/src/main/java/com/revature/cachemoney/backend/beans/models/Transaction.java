package com.revature.cachemoney.backend.beans.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@JoinColumn(name = "account_id")
	@ManyToOne
	private Account accountId;

	@Column(name = "description")
	private String description;

	// look into date datatype, and how to/accept formatting...
	@Column(name = "transaction_date")
	private Date transactionDate;

	// look into datatype to take care of floating point arithmetic
	@Column(name = "transaction_amount")
	private BigDecimal transactionAmount;

	@Column(name = "ending_balance")
	private BigDecimal endingBalance;

	public Transaction(Account account, String description, Date transaction_date,
			BigDecimal transaction_amount, BigDecimal ending_balance) {
		this.accountId = account;
		this.description = description;
		this.transactionDate = transaction_date;
		this.transactionAmount = transaction_amount;
		this.endingBalance = ending_balance;
	}

	
	public Transaction(Account account, String description, BigDecimal transaction_amount, BigDecimal ending_balance) {

		this.accountId = account;
		this.description = description;
		this.transactionAmount = transaction_amount;
		this.endingBalance = ending_balance;
	}

	public Transaction(String description, BigDecimal transaction_amount, BigDecimal ending_balance) {

		this.description = description;
		this.transactionAmount = transaction_amount;
		this.endingBalance = ending_balance;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "TRANSACTION";
		s += "{";
		s += this.transactionId + "\n";
		s += this.accountId + "\n";
		s += this.description + "\n";
		s += this.transactionDate + "\n";
		s += this.transactionAmount + "\n";
		s += this.endingBalance + "\n";
		s += "}";

		return s;
	}

}
    


