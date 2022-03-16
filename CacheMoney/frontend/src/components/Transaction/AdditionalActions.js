import React, { useState } from "react";
import Deposit from "./Deposit.js";
import Withdraw from "./Withdraw.js";
import Transfer from "./Transfer.js";
import store from "../../store/Store.js";

function AdditionalActions(props) {
	const [submenu, setSubMenu] = useState("");

	let additionalContent = (submenu) => {
		switch (submenu) {
			case "deposit":
				return <Deposit doTransactionDone={props.doTransactionDone} />;
			case "withdraw":
				return <Withdraw doTransactionDone={props.doTransactionDone} />;
			case "transfer":
				return <Transfer doTransactionDone={props.doTransactionDone} />;
			default:
				return;
		}
	};

	// User has clicked a button (deposit, withdraw, transfer)
	const handleOptionSelection = (event) => {
		setSubMenu(event.target.value);
		additionalContent(submenu);
	};

	// This is the worst idea I've ever had.
	// When triggered by a transaction completed, it saves the date in ms to store.
	// Account should subscribe to the store to update when it notices it is changed.
	const handleAccountUpdate = () => {
		store.dispatch({
			type: "UPDATE_ACCOUNTS_PLEASE",
			payload: Date.now(),
		});
		// UPDATE_ACCOUNTS_PLEASE
		//console.log(store.getState().accountReducer.someData);
	};

	return (
		<div className="account-details">
			<div className="account-additional-options">
				<button
					className="account-option"
					value="deposit"
					onClick={handleOptionSelection}
				>
					Deposit
				</button>
				<button
					className="account-option"
					value="withdraw"
					onClick={handleOptionSelection}
				>
					Withdraw
				</button>
				<button
					className="account-option"
					value="transfer"
					onClick={handleOptionSelection}
				>
					Transfer
				</button>
			</div>
			<div className="additional-options-content">
				{additionalContent(submenu)}
			</div>
		</div>
	);
}

export default AdditionalActions;
