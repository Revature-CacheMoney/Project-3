import React, { useState, useEffect } from "react";
import userStore from "../store/Store.js";
import axios from "axios";
import { useDarkMode } from "./style/useDarkMode";
import { ThemeProvider } from "styled-components";
import { GlobalStyles } from "../components/style/GlobalStyles";
import Toggle from "./style/Toggle";
import { lightTheme, darkTheme } from "../components/style/Themes";
import { useNavigate } from "react-router-dom";
import config from "../config.js";

//Implementing patch to fix Axios DDoS vulnerability.
import rateLimit from 'axios-rate-limit';
const http = rateLimit(axios.create(), { maxRequests: 2, perMilliseconds: 1000, maxRPS: 2 })

// The Signin component is the login form the user sees after pressing the "sign in" button.
// An API call should be made to test for successful login credentials.
// The user's info (partial) should be persisted throughout the app.
function SigninView() {
	const navigate = useNavigate();

	const [formData, setFormData] = useState({
		username: "",
		password: "",
	});
	const handleLogin = () => {
		doLogin();
	};

	const handleChange = (event) => {
		event.preventDefault();
		setFormData({ ...formData, [event.target.name]: event.target.value });
		//
	};

	function doLogin() {
		const validCharacterPattern = /^[a-zA-Z0-9@^%$#/\\,;|~._-]{8,50}$/;
		let responseStatus;
		let responseData;
		let responseHeaders;
		// 		(From Gideon) I am adding validation right here, before the entered information from formData is used for any commands, to
		//	validate them. If they fail to pass validation, they get cleared so no injection attacks can go through.
		if (!validCharacterPattern.test(formData.username)) {
			// username is invalid. Replace with something that both cannot be a possible username and won't cause an injection.
			formData.username = "Invalid!String";
		}
		if (!validCharacterPattern.test(formData.password)) {
			// password is invalid. Replace with something that both cannot be a possible username and won't cause an injection.
			formData.password = "Invalid!String";
		}
		let user = {
			username: formData.username,
			password: formData.password,
		};
		const url = config.url;
		axios
			.post(`${url}users/login`, user)
			.then((response) => {
				responseData = response.data;
				responseStatus = response.status;
				responseHeaders = response.headers;
				if (responseStatus !== 200 || responseData.user_id === null) {
					console.log("Login failed - bad username/password");
					document.getElementById("login-error-box").innerHTML =
						"Error: incorrect username or password.";
					let loginInputArr = document.getElementsByClassName("login-input");
					for (let i = 0; i < loginInputArr.length; i++) {
						loginInputArr[i].style.border = "2px solid red";
						loginInputArr[i].style.boxShadow = "-4px 4px 0px #b04050";
					}
					//alert("Invalid login attempt.");
				} else {
					console.log("Response body from login API: ", responseData);
					userStore.dispatch({
						type: "UPDATE_ID",
						payload: responseData.userId,
					});
					userStore.dispatch({
						type: "UPDATE_USERNAME",
						payload: responseData.username,
					});
					userStore.dispatch({
						type: "UPDATE_NAME_FIRST",
						payload: responseData.firstName,
					});
					userStore.dispatch({
						type: "UPDATE_NAME_LAST",
						payload: responseData.lastName,
					});
					userStore.dispatch({
						type: "UPDATE_TOKEN",
						payload: responseHeaders.jwt,
					});

					navigate("/main");
				}
			})
			.catch((error) => {
				console.error(`Error: ${error}`);
			});
	}

	const [theme, themeToggler, mountedComponent] = useDarkMode();
	const themeMode = theme === "light" ? lightTheme : darkTheme;

	if (!mountedComponent) return <div />;
	return (
		<ThemeProvider theme={themeMode}>
			<>
				<GlobalStyles />
				<div className="container-view login-outer-container">
					<div className="login-inner-container">
						<div className="login-content-box">
							<Toggle
								theme={theme}
								id="login-theme-button"
								toggleTheme={themeToggler}
							/>
							<h2 className="logo-smaller">CacheMoney</h2>
							<div className="login-white-box">
								<div className="login-white-box-column">
									<p className="subheader-centered">
										Please Enter Your Credentials
									</p>
									<span id="login-error-box"></span>
									<label htmlFor="username">Username:</label>
									<input
										type="text"
										className="login-input"
										name="username"
										id="username"
										onChange={handleChange}
										required
									/>
									<label htmlFor="password">Password:</label>
									<input
										type="password"
										className="login-input"
										name="password"
										id="password"
										onChange={handleChange}
										required
									/>
									<button
										className="login"
										id="login-button"
										type="submit"
										onClick={handleLogin}
									>
										SIGN IN
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</>
		</ThemeProvider>
	);
}

export default SigninView;
