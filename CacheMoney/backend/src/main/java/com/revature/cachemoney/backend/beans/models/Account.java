package com.revature.cachemoney.backend.beans.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Object for interacting with Users.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "balance")
	private Double balance;

	@Column(name = "name")
	private String name;

	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne
	private User user;

	public Account(String type) {
		this.type = type;
		this.name = "";
		this.balance = 0.00;
	}

	public Account(String type, String name) {
		this.type = type;
		this.name = name;
		this.balance = 0.00;

	}
}
