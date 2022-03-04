import React from "react";
import Navigation from "./NavBar.jsx";
import Footer from "./Footer.js";

function MainPageView2() {
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
			</div>

			<Footer />
		</div>
	);
}

export default MainPageView2;
