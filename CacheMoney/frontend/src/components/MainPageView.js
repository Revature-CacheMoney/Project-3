import React, { useState } from "react";
import Navigation from "./NavBar";
import Footer from "./Footer.js";

function MainPageView(props) {
	//let accountPreview = [];
	//   let [accountPreview, setAccountPreview] = useState([]);
	let [accountPreview, setAccountPreview] = useState([
		"Checking",
		"Savings",
		"California Move Stash",
		"Wu-tang",
	]);

	/*
	// Just adding this to make the keys happy for sample code
	for (let i = 0; i < 5; i++) {
		accountPreview.push(<AccountObject key={"account" + i} />);

	} */

	/*
	// This displays a list of account names
	let showAccountPreview = accountPreview.map((account) => {
		return (
			<li key="main-page-account-info" className="list-accounts">
				{account}
			</li>
		);
	}); */

	return (
		<div className="container-view main-container-content">
			<div className="headerContainer">
				<Navigation />
			</div>
			<div>
				<h2> Accounts </h2>
				<hr />

				<h2> CACHE FINANCE</h2>

				{/* showAccountPreview */}
				{/* {accountPreview} */}

				<h2> CACHE RECREATION</h2>
				{/* <AccountObject /> */}
				<hr />
			</div>
			<Footer />
		</div>
	);
}

export default MainPageView;
