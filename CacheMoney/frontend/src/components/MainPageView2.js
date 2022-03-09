import React from "react";
import Navigation from "./NavBar.jsx";
import Footer from "./Footer.js";
import AccountList from "./Account/AccountList.js";
import userStore from "../store/Store.js";
import NavBar from "./NavBar.js";

function MainPageView2() {
	console.log("Data store: ", userStore.getState());
	const username = "REVMAN3076";
	return (
		<div className="main-page-container container-view">
			<div className="header">
				<div className="header-welcome-box">
					Welcome, <br />
					<span className="header-username">{username}</span>
				</div>
				<a href="#">
					<button id="logout-button">Log Out</button>
				</a>
			</div>

			{/* <Navigation /> */}
			<NavBar />

			<div className="main-page-content">
				{/******* Insert body content here ********/}
				<AccountList />
			</div>

			<Footer />
		</div>
	);
}

export default MainPageView2;
