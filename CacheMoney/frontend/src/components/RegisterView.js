import axios from "axios";
import React from "react";
import { Link, useNavigate } from "react-router-dom";

// The registrration component handles the registration form for new users.
// The info is persisted in the database and locally (partial).

function RegisterView() {
	const navigate = useNavigate();

	// handleSubmit: What happens when user presses the "submit" button on reg. form
	const handleSubmit = (event) => {
		event.preventDefault();

		// Check if username is at least 8 charas long
		// This is just a quick and dirty fix until real validation is implemented
		const username = document.getElementById("username").value;

		// validate password (do they match?)
		const p1 = document.getElementById("password1").value;
		const p2 = document.getElementById("password2").value;

		if (p1 === p2) {
			const info = {
				firstName: document.getElementById("firstname").value,
				lastName: document.getElementById("lastname").value,
				email: document.getElementById("email").value,
				username: document.getElementById("username").value,
				password: document.getElementById("password1").value,
			};
			// Validate information
			if (validateInput(info)) {
				// submit stuff
				console.log("Information being sent:  ", info);
				doRegistration(info);
				// if successful - a string is returned? (as of 3/2)
			}
		} else {
			alert("Sorry, your passwords do not match.");
		}
	};

	function registrationError(id, spanId, errorText) {
		let ele = document.getElementById(id);
		ele.style.border = "2px solid red";
		ele.style.boxShadow = "-4px 4px 0px #b04050";
		let errorSpan = document.getElementById(spanId);
		errorSpan.innerHTML = errorText;
		errorSpan.style.color = "red";
		errorSpan.style.margin = "0px 0px 0px 12px";
	}

	// Checks input against validation (should be same patterns as used in backend)
	function validateInput(info) {

		let regValidity = true;

		const { firstName, lastName, email, username, password } = info;
		// Test the firstname, lastname for validity - ex. no empty strings
		const namePattern = /^[a-zA-Z -]+$/;
		if (!namePattern.test(firstName)) {
			regValidity = false;
			registrationError("firstname", "firstname-span", "Invalid first name.  Please check the spelling and try again.");

		}
		if (!namePattern.test(lastName)) {
			regValidity = false;
			registrationError("lastname", "lastname-span", "Invalid last name.  Please check the spelling and try again.");
		}

		const emailPattern =
			/^[a-zA-Z0-9._-]+@{1}[a-zA-Z0-9-_]+[.]{1}[a-zA-Z0-9]+[a-zA-Z_.-]*$/;
		if (!emailPattern.test(email)) {
			regValidity = false
			registrationError("email", "email-span", "Invalid email address.  Please check your input and try again.");
		}

		const usernamePattern = /^[a-zA-Z0-9@~._-]{8,}$/;
		if (!usernamePattern.test(username)) {
			regValidity = false;
			registrationError("username", "username-span", "Invalid username.  Usernames should be between 8-255 characters in length and use alphanumeric / select symbols..");
		}

		const passwordPattern = /^[a-zA-Z0-9@^%$#/\\,;|~._-]{8,50}$/;
		if (!passwordPattern.test(password)) {
			regValidity = false;
			registrationError("password1", "password1-span", "Invalid password.   Passwords should be between 8-50 characters in length and use alphanumeric / select symbols..");
			registrationError("password2", "password2-span", "Invalid password.   Passwords should be between 8-50 characters in length and use alphanumeric / select symbols..");
		}

		if (!regValidity) {
			document.getElementById("registration-error").innerHTML =
			"Error: Please correct invalid input.";
			return false;
		}

		return true;
	}

	// doRegistration: does the actual registration call
	function doRegistration(info) {
		let responseData;
		const url = "http://localhost:8080/";

		axios
			.post(`${url}users/`, info)
			.then((response) => {
				console.log(response);
				responseData = response.data;
				if (responseData.result) {
					console.log("Registration successful");
					doLoginToMain();
				} else {
					console.log("Some error occurred during registration.");
				}
				/* Commented out b/c registration response may change, but code is valid/from login */
				/*
				if (responseData.user_id === null) {
					console.log("Login failed - bad usernmae/password");
					document.getElementById("login-error-box").textContent =
						"Error: incorrect username or password.";
					//alert("Invalid login attempt.");
				} else {
					doLoginToMain();
				} */ // end working, commented-out code
				doLoginToMain();
				// Should probably redirect to login screen, not straight to main pages
			})
			.catch((error) => console.error(`Error: ${error}`));
		//console.log(responseData);
	}

	// TODO: this should route back to index page with note to login
	const doLoginToMain = () => {
		// let history = useHistory ();
		navigate("/main");
	};

	return (
		<div className="container-view login-outer-container">
			<div className="login-inner-container">
				<div className="login-content-box">
					<h2 className="logo-smaller" id="register-logo">
						CacheMoney
					</h2>
					<div id="register-white-box" className="login-white-box">
						<div className="login-white-box-column">
							<div className="error-container">
								<span id="registration-error"></span>
							</div>
							<div id="registration-name-boxes">
								<div id="box-L" className="reg-name-box">
									<label htmlFor="firstname" id="label-L">
										First name:
										<span className="detail-text" id="firstname-span"></span>
									</label>
									<input type="text" name="firstname" className="reg-input-box" id="firstname" />
								</div>

								<div id="box-R" className="reg-name-box">
									<label htmlFor="lastname" id="label-R">
										Last name:
										<span className="detail-text" id="lastname-span"></span>
									</label>
									<input type="text" name="lastname" className="reg-input-box" id="lastname" />
								</div>
							</div>

							<div className="reg-field-box">
								<label htmlFor="email">
									Email:
									<span className="detail-text" id="email-span">
										*must be unregistered valid email
									</span>
								</label>
								<input type="text" name="email" className="reg-input-box" id="email" />
							</div>

							<div className="reg-field-box">
								<label htmlFor="username">
									Username: 
									<span className="detail-text" id="username-span">*must be unique</span>
								</label>
								<input type="text" name="username" className="reg-input-box" id="username" />
							</div>

							<div className="reg-field-box">
								<label htmlFor="password">
									Password:
									<span className="detail-text" id="password1-span"></span>
								</label>
								<input
									type="text"
									name="password1"
									id="password1"
									className="password-box reg-input-box"
								/>
							</div>

							<div className="reg-field-box">
								<label htmlFor="password2">
									Confirm password:
									<span className="detail-text" id="password2-span"></span>
								</label>
								<input
									type="text"
									name="password2"
									id="password2"
									className="password-box reg-input-box"
								/>
							</div>

							<input type="submit" value="Register" onClick={handleSubmit} />
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}

export default RegisterView;

/* Currently disabled (some bug w/ highlighting - needs troubleshooting) */
// This compares the two password entry fields.  If they do not match,
// the boxes are given a red border and the submit button is disabled.
// TODO: Probably want a clearer (text) indicator stating the problem.
// optional: Make the password type "password", give option to flip between visibility
/*
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
	}; */
