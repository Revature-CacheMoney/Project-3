import React from "react";

function AdditionalActions() {
	const additionalContent = () => {};

	return (
		<div class="account-details">
			<div class="account-additional-options">
				<button class="account-option">Deposit</button>
				<button class="account-option">Withdrawal</button>
				<button class="account-option">Transfer</button>
			</div>
			<div class="additional-options-content"></div>
		</div>
	);
}

export default AdditionalActions;
