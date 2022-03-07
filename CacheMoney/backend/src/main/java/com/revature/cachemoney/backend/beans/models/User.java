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
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer user_id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	public User(String firstName, String lastName, String email, String password, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.username = username;
	}

	@Override
	public String toString() {
		String s = "";
		s += "USER";
		s += "{";
		s += this.user_id + "\n";
		s += this.firstName + "\n";
		s += this.lastName + "\n";
		s += this.email + "\n";
		s += this.username + "\n";
		s += this.password +"\n";
		s += "}";

		return s;
	}
}
