import React from "react";
import Deposit from "./Deposit.js";
import Withdraw from "./Withdraw.js";
import Transfer from "./Transfer.js";

function AdditionalActions() {
	let additionalContent = (type) => {
		switch (type) {
			case "deposit":
				return <Deposit />;
			case "withdraw":
				return <Withdraw />;
			case "transfer":
				return <Transfer />;
			default:
				return;
		}
	};

	const handleOptionSelection = (event) => {
		console.log(event.target.value);
		additionalContent(event.target.value);
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
			<div className="additional-options-content">{additionalContent}</div>
		</div>
	);
}

export default AdditionalActions;
