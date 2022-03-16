import AdditionalActions from "../Transaction/AdditionalActions.js";
import TransactionFilter from "../Transaction/TransactionFilter.js";
import AccountList from "./AccountList.js";
import React, { useState } from "react";

// This is a wrapper showing the account list and space for transactions
function AccountDisplay() {
	const [account, setAccount] = useState({});
	const [savedAmount, setAmount] = useState(0);

	// This occurs when the user has selected an account
	// it displays additional options (deposit, withdraw, transfer)
	const showAdditionalActions = () => {
		if (account.name) {
			return (
				<div className="account-options-container">
					<div id="account-display-header">
						<h2>{account.name}</h2>
					</div>
					<AdditionalActions doTransactionDone={handleTransactionDone} />
					<br />
					<TransactionFilter />
				</div>
			);
		} else {
			return;
		}
	};

	// This updates the title on the additional menu (right side)
	const handleTitleUpdate = (newAccount) => {
		setAccount(newAccount);
	};

	// This handler communicates to accountlist to do a reload
	// It is ultimately called by Deposit, Withdraw, Transfer components onSubmit
	const handleTransactionDone = (amount) => {
		console.log("Update the account");
		console.log(amount);
		setAmount(amount);
		console.log(savedAmount);
	};

	return (
		<div className="account-container">
			<AccountList doTitleUpdate={handleTitleUpdate} someAmount={savedAmount} />
			<div className="transaction-container">{showAdditionalActions()}</div>
		</div>
	);
}

// This is a wrapper showing the account list and space for transactions
export default AccountDisplay;
