import React from "react";
import Navigation from "./NavBar.jsx";
import Footer from "./Footer.js";
import AccountList from "./Account/AccountList.js";
import userStore from "../store/Store.js";

function MainPageView2() {
	const userData = userStore.getState().userReducer;
	console.log("Data store: ", userData);
	//const username = "REVMAN3076";
	console.log("Name: ", userData.firstName);
	return (
		<div className="main-page-container container-view">
			<div className="header">
				<div className="header-welcome-box">
					Welcome, <br />
					<span className="header-username">
						{userData.firstName} {userData.lastName}
					</span>
				</div>
				<a href="#">
					<button id="logout-button">Log Out</button>
				</a>
			</div>

			{/* <Navigation /> */}
			<div className="navigation-bar">
				<a href="/accounts">
					<span className="navigation-link">Accounts</span>
				</a>
				<a href="/accounts/create">
					<span className="navigation-link">Create Account</span>
				</a>
				<a href="/transfer">
					<span className="navigation-link">Transfer Money</span>
				</a>
				<div className="settings">
					<a href="#">
						<span className="navigation-link">Settings</span>
					</a>
				</div>
			</div>

			<div className="main-page-content">
				{/******* Insert body content here ********/}
				<AccountList />
			</div>

			<Footer />
		</div>
	);
}

export default MainPageView2;
