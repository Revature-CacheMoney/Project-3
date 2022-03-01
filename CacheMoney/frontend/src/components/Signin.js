import React, { useState } from "react";
import "../css/Signin.css";

function Signin() {
	// Test stuff .
	let [input, setInput] = useState([""]);

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
		const url = "http://localhost:8080/controller/login";

		let stuff;
		try {
			let promise = await fetch(url, {
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
			<input type="submit" value="Register" onClick={login} />
			{console.log(login)}
		</div>
	);
}

export default Signin;
