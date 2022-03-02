import React from "react";
import Navigation from "../TestNav";

// The Account component should hold multiple transactions related to this account.
// Also may contain other related account info (name, account number) and possibly link to do
// transfers as well (or another parent component?)
function AccountsView(props) {
	return (
		<>
		<Navigation />
			<div id="account-container">
				<h3>Account Header</h3>
				<p>Diffirent accounts</p>
			</div>
		</>
	);
}

export default AccountsView;
