import React, { useState } from "react";
import "../css/Signin.css";

// The Signin component is the login form the user sees after pressing the "sign in" button.
// An API call should be made to test for successful login credentials.
// The user's info (partial) should be persisted throughout the app.
function SigninView() {
	// Test stuff .
	let [input, setInput] = useState([""]);

	// This grabs the user's input from the form.
	const login = (event) => {
		event.preventDefault();
		const username = document.getElementById("username");
		const password = document.getElementById("password");
		const result = {
			username,
			password,
		};
		setInput(result);
	};

	const inputStuff = (event) => {
		setInput(event.target.value);
	};

	// Not tested or implemented yet.  Endpoint is probably incorrect.
	async function doLogin(user) {
		const apiEndpoint = "http://localhost:8080/users/login";

		let stuff;
		try {
			let promise = await fetch(apiEndpoint, {
				method: "POST",
				body: JSON.stringify(user),
				headers: {
					"Content-Type": "application/json",
				},
			})
				.then((response) => response.json())
				.then((result) => (stuff = result));
		} catch (error) {
			console.log("Error: \n" + error);
			//console.log("Response: \n" + response);
		}
		console.log("raw result: ", stuff);

		if (stuff.result == true) {
			console.log("signing in was a success!");
			//doSignin();
			// endpoint will return a json object w/ user data
			// Store data received from backend (user data) (redux)s
		} else {
			alert("Sorry, wrong user ID and password!");
		}
	}

	return (
		<div id="signin">
			<label htmlFor="username">Username:</label>
			<input
				type="text"
				onChange={inputStuff}
				className="username"
				id="username"
			/>
			<label htmlFor="password">Password:</label>
			<input type="text" className="password" id="password" />
			<input type="submit" value="Log in" onClick={login} />
			{console.log(login)}
		</div>
	);
}

export default SigninView;
