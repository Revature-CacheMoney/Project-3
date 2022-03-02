import React, { useState } from "react";
import userStore from "../store/Store.js";
import { createBrowserHistory } from "history";

// The Signin component is the login form the user sees after pressing the "sign in" button.
// An API call should be made to test for successful login credentials.
// The user's info (partial) should be persisted throughout the app.
function SigninView() {
	// Test stuff .
	//let [input, setInput] = useState([""]);

	// This grabs the user's input from the form.
	const login = (event) => {
		event.preventDefault();
		const username = document.getElementById("username").value;
		const password = document.getElementById("password").value;
		const credentials = {
			username,
			password,
		};
		console.log("credentials: ", credentials);
		//let result = verifyCredentials(credentials);
		//setInput(result);
		// if successful - store data
		doLoginToMain();
		// else - display error messages
	};

	const inputStuff = (event) => {
		//setInput(event.target.value);
	};

	return (
		<div id="signin-container">
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

// Not tested or implemented yet.  Endpoint is probably incorrect.
async function verifyCredentials(credentials) {
	const apiEndpoint = "http://localhost:8080/users/login";

	const exampleRequestData = {
		username: "someusername",
		password: "password",
	};
	// if id is null - failed login

	let stuff;
	try {
		let promise = await fetch(apiEndpoint, {
			method: "POST",
			body: JSON.stringify(credentials),
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
		doLoginToMain();
	} else {
		console.log("Wrong user ID and password!");
	}
}

function doLoginToMain() {
	// pushing new url to react-router's history
	let history = createBrowserHistory();
	history.listen((location, action) => {
		// this is called whenever new locations come in
		// the action is POP, PUSH, or REPLACE
		//this.props.history.push('/posts/');
		history.push("/main");
	});
	console.log("history", history);
}
