import React, { useState } from "react";
import userStore from "../store/Store.js";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import config from "../config.js";


// The Signin component is the login form the user sees after pressing the "sign in" button.
// An API call should be made to test for successful login credentials.
// The user's info (partial) should be persisted throughout the app.
function SigninView() {
	const navigate = useNavigate();


	const [formData,setFormData] = useState({
		username:"",
		password:""
	})
	const handleLogin = () => {
	
		doLogin();
	};


	const handleChange = (event) => {
		event.preventDefault();
		setFormData({...formData,[event.target.name]:event.target.value})
		//
	}

	function doLogin() {
		let responseStatus;
		let responseData;
		let user = {
			username: formData.username,
			password: formData.password
		}
		const url = config.url;
		axios
			.post(`${url}users/login`, user)
			.then((response) => {
				responseData = response.data;
				responseStatus = response.status;
				if (responseStatus !== 200 || responseData.user_id === null) {
					console.log("Login failed - bad username/password");
					document.getElementById("login-error-box").innerHTML = "Error: incorrect username or password.";
					let loginInputArr = document.getElementsByClassName("login-input");
					for (let i = 0; i < loginInputArr.length; i++){
						loginInputArr[i].style.border = "2px solid red";
						loginInputArr[i].style.boxShadow = "-4px 4px 0px #b04050";
					}
					//alert("Invalid login attempt.");
				} else {
					console.log("Response from login API: ", responseData);
					userStore.dispatch({
						type: "UPDATE_ID",
						payload: responseData.user_id,
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
					
					navigate("/main");
				}
			})
			.catch((error) => {
				console.error(`Error: ${error}`);
			});
	}

	

	return (
		<div className="container-view login-outer-container">
			<div className="login-inner-container">
				<div className="login-content-box">
					<h2 className="logo-smaller">CacheMoney</h2>
					<div className="login-white-box">
						<div className="login-white-box-column">
							<p className="subheader-centered">
								Please Enter Your Credentials
							</p>
							<span id="login-error-box"></span>
							<label htmlFor="username">Username:</label>
							<input type="text" className="login-input" name="username" id="username" onChange={handleChange} required />
							<label htmlFor="password">Password:</label>
							<input type="text" className="login-input" name="password" id="password" onChange={handleChange} required />
							<button className="login" type="submit" onClick={handleLogin}>
								SIGN IN
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}


export default SigninView;

