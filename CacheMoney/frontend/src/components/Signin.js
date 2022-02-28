import React, { useState } from "react";
import "../css/Signin.css";

function Signin() {
	// Test stuff
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
