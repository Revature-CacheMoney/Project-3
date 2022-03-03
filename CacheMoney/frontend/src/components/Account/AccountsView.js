import React from "react";
import Navigation from "../NavBar";
import Footer from "../Footer.js";

// The Account component should hold multiple transactions related to this account.
// Also may contain other related account info (name, account number) and possibly link to do
// transfers as well (or another parent component?)
function AccountsView(props) {
	return (
		<div className="container-view main-container-content">
			<Navigation />
			<div id="account-container">
				<h3>Account Header</h3>
				<p>Diffirent accounts</p>
			</div>
			<Footer />
		</div>
	);
}

export default AccountsView;
