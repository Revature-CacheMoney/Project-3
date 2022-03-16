import AdditionalActions from "../Transaction/AdditionalActions.js";
import TransactionFilter from "../Transaction/TransactionFilter.js";
import AccountList from "./AccountList.js";
import React, { useState } from "react";
import "../../css/App.css";

// This is a wrapper showing the account list and space for transactions
function AccountDisplay() {
	const [account, setAccount] = useState({});

	// This occurs when the user has selected an account
	// it displays additional options (deposit, withdraw, transfer)
	const showAdditionalActions = () => {
		console.log("showAdditionalActions");
		console.log(account);
		if (account.name) {
			// console.log("received in SAA: ", account);
			return (
				<div className="account-options-container">
					<div id="account-display-header">
						<h2>{account.name}</h2>
					</div>
					<AdditionalActions />
					<br />
					<TransactionFilter />
				</div>
			);
		} else {
			return;
		}
	};
	// store.subscribe(showAdditionalActions);

	const handleTitleUpdate = (newAccount) => {
		setAccount(newAccount);
		// console.log("received in HTU: ", newAccount);
	};

	return (
		<div className="account-container">
			<AccountList doTitleUpdate={handleTitleUpdate} />
			<div className="transaction-container">{showAdditionalActions()}</div>
		</div>
	);
}

// This is a wrapper showing the account list and space for transactions
export default AccountDisplay;
