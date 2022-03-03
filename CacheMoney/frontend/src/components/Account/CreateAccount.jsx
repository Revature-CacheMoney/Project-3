import React from "react";
import Navigation from "../NavBar";
import Footer from "../Footer.js";

export default function AccountType() {
	return (
		<div className="container-view main-container-content">
			<Navigation />
			<div className="account-type-container">
				<h4>NAME OF THE ACCOUNT YOU WOULD LIKE TO CREATE</h4>
				<h1>REVMAN3076</h1>
				<p>HEY LOOK AT ME I CAN CREATE AS MANY ACCOUNTS AS I WANT</p>
			</div>
			<Footer />
		</div>
	);
}
