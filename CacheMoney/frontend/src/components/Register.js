import React from "react";
import "../css/Register.css";

// The registrration component handles the registration form for new users.
// The info is persisted in the database and locally (partial).
function Register() {
	const handleSubmit = (event) => {
		event.preventDefault();

		// validate password
		const p1 = document.getElementById("password");
		const p2 = document.getElementById("password2");

		if (p1 === p2) {
			const info = {
				firstname: document.getElementById("firstname"),
				lastname: document.getElementById("lastname"),
				email: document.getElementById("email"),
				username: document.getElementById("username"),
				password: document.getElementById("password"),
			};
			// submit stuff
		} else {
			alert("Sorry, your passwords do not match.");
		}
	};

	// This compares the two password entry fields.  If they do not match,
	// the boxes are given a red border and the submit button is disabled.
	// TODO: Probably want a clearer (text) indicator stating the problem.
	// optional: Make the password type "password", give option to flip between visibility
	const checkPasswordEntry = () => {
		const passwordNodes = document.getElementsByClassName("password-box");
		const password1 = passwordNodes[0].value;
		const password2 = passwordNodes[1].value;

		if (password1 === password2) {
			for (let i = 0; i < 2; i++) {
				passwordNodes[i].classList.remove("password-error");
				passwordNodes[i].classList.add("password-ok");
				document.getElementById("submit").disabled = false;
			}
		} else {
			for (let i = 0; i < 2; i++) {
				// the passwords do not match
				passwordNodes[i].classList.remove("password-ok");
				passwordNodes[i].classList.add("password-error");
				document.getElementById("submit").disabled = true;
			}
		}
	};

	return (
		<div id="register-page">
			<label htmlFor="firstname">First name:</label>
			<input type="text" name="firstname" id="firstname" />
			<label htmlFor="lastname">Last name:</label>
			<input type="text" name="lastname" id="lastname" />
			<label htmlFor="email">Email:</label> *must be unregistered valid email
			<input type="text" name="email" id="email" />
			<label htmlFor="username">Username:</label> *must be unique
			<input type="text" name="username" id="username" />
			<label htmlFor="password">Password:</label>
			<input
				type="text"
				name="password1"
				id="password1"
				className="password-box"
				onBlur={checkPasswordEntry}
			/>
			<label htmlFor="password2">Confirm password:</label>
			<input
				type="text"
				name="password2"
				id="password2"
				className="password-box"
				onBlur={checkPasswordEntry}
			/>
			<input type="submit" value="Register" id="submit" />
		</div>
	);
}

export default Register;
