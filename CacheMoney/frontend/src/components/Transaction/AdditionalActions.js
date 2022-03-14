import React from "react";
import TransactionFilter from "./TransactionFilter.js";

function AdditionalActions() {
	return (
		<div class="account-details">
			<TransactionFilter />
			<div class="account-additional-options">
				<button class="account-option">Deposit</button>
				<button class="account-option">Withdrawal</button>
				<button class="account-option">Transfer</button>
			</div>
		</div>
	);
}

export default AdditionalActions;
