import AdditionalActions from "../Transaction/AdditionalActions.js";
import TransactionFilter from "../Transaction/TransactionFilter.js";
import AccountList from "./AccountList.js";
import React, { useEffect, useState } from "react";
import "../../css/Account.css";
import store from "../../store/Store.js";

// This is a wrapper showing the account list and space for transactions
function AccountDisplay() {
	const [account, setAccount] = useState({});

	let currentlySelectedAccount =
		store.getState().accountReducer.currentAccountId;

	// This occurs when the user has selected an account
	// it displays additional options (deposit, withdraw, transfer)
	const showAdditionalActions = () => {
		if (currentlySelectedAccount) {
			return (
				<>
					<div id="account-display-header">
						<h2>{account.name}</h2>
					</div>
					<AdditionalActions />
					<br />
					<TransactionFilter />
				</>
			);
		} else {
			return;
		}
	};
	store.subscribe(showAdditionalActions);

	const handleTitleUpdate = (currentlySelectedAccount) => {
		setAccount(currentlySelectedAccount);
		//console.log("received: ", currentlySelectedAccount);
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
