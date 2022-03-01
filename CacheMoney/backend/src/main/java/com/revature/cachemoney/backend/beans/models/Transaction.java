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
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transaction_id;

	@Column(name = "account_id")
	private Integer account_id;

	@Column(name = "description")
	private String description;

	// look into date datatype, and how to/accept formatting...
	@Column(name = "transaction_date")
	private Date transaction_date;

	// look into datatype to take care of floating point arithmetic
	@Column(name = "transaction_amount")
	private BigDecimal transaction_amount;

	@Column(name = "ending_balance")
	private BigDecimal ending_balance;

	public Transaction(Integer transaction_id, Integer account_id, String description, Date transaction_date,
			BigDecimal transaction_amount, BigDecimal ending_balance) {
		this.transaction_id = transaction_id;
		this.account_id = account_id;
		this.description = description;
		this.transaction_date = transaction_date;
		this.transaction_amount = transaction_amount;
		this.ending_balance = ending_balance;
	}

	public void addTransaction(Transaction trns) {
	}

	public void removeTransaction(Transaction trns) {
	}

	@Override
	public String toString() {
		String s = "";
		s += "TRANSACTION";
		s += "{";
		s += this.transaction_id + "\n";
		s += this.account_id + "\n";
		s += this.description + "\n";
		s += this.transaction_date + "\n";
		s += this.transaction_amount + "\n";
		s += this.ending_balance + "\n";
		s += "}";

		return s;
	}

}
    


