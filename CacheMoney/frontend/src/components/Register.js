import React from "react";
import "../css/Register.css";

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
			alert("Sorry, your username and password are incorrect");
		}
	};

	return (
		<div id="register-page">
			<label for="firstname">First name:</label>
			<input type="text" name="firstname" id="firstname" />
			<label for="lastname">Last name:</label>
			<input type="text" name="lastname" id="lastname" />
			<label for="email">Email:</label> *must be unregistered valid email
			<input type="text" name="email" id="email" />
			<label for="username">Username:</label> *must be unique
			<input type="text" name="username" id="username" />
			<label for="password">Password:</label>
			<input type="text" name="password" id="password" />
			<label for="password2">Confirm password:</label>
			<input type="text" name="password2" id="password2" />
			<input type="submit" value="Register" />
		</div>
	);
}

export default Register;
