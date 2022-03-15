import React, { useState } from "react";
import Deposit from "./Deposit.js";
import Withdraw from "./Withdraw.js";
import Transfer from "./Transfer.js";

function AdditionalActions() {
	const [submenu, setSubMenu] = useState("");

	let additionalContent = (submenu) => {
		switch (submenu) {
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
		setSubMenu(event.target.value);
		console.log(submenu);
		additionalContent(submenu);
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
