import React, { useEffect, useState } from "react";
import Footer from "./Footer.js";
import AccountList from "./Account/AccountList.js";
import userStore from "../store/Store.js";
import NavBar from "./NavBar.js";
import { useNavigate } from "react-router-dom";
import Transfer from "./Transaction/Transfer.js";
import CreateAccount from "./Account/CreateAccount.js";

function MainPageView2() {
	const navigate = useNavigate();
	let userData = userStore.getState().userReducer;

	const [page, setPage] = useState("account-overview");

	const handleLogout = (event) => {
		userStore.dispatch({
			type: "LOGOUT_USER",
			payload: "",
		});

		navigate("/");
	};

	const updateMainPageContent = (event) => {
		console.log(event.target.id + " was clicked");
		setPage(event.target.id);
		mainPageContentComponent(event.target.id);
	};

	const mainPageContentComponent = () => {
		switch (page) {
			case "account-overview":
				return <AccountList />;
			case "create-account":
				return <CreateAccount />;
			case "transfer-money":
				return <Transfer />;
			default:
				return <AccountList />;
		}
	};

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
					<button id="logout-button" onClick={handleLogout}>
						{" "}
						Log Out
					</button>
				</a>
			</div>

			<NavBar handleClick={updateMainPageContent} />

			<div className="main-page-content">
				{/******* Insert body content here ********/}
				{mainPageContentComponent()}
			</div>

			<div className="popup-overlay hidden">
				{/* Popup to do a deposit/withdrawal */}
			</div>

			<Footer />
		</div>
	);
}

export default MainPageView2;
