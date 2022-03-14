/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import axios from "axios";
import React, { useEffect, useState } from "react";
import config from "../../config.js";
import store from "../../store/Store.js";
import CurrencyFormat from "react-currency-format";
import "../../css/Account.css";
import AdditionalActions from "../Transaction/AdditionalActions.js";

function AccountList(props) {
	// local transaction state
	const [accounts, setAccounts] = useState([]);
	// setAccounts is the setter function
	// read state with accounts

	// url
	const url = config.url;

	// effect hook
	useEffect(() => {
		getAllAccounts();
	}, [accounts]);

	const getAllAccounts = () => {
		//${url}users/accounts/${store.getState().userId}
		axios
			.get(`${url}users/accounts`, {
				headers: {
					token: store.getState().userReducer.token,
					userId: store.getState().userReducer.userId,
				},
			})
			.then((response) => {
				const allAccounts = response.data;

				// Modifying the account object to also have whether displayed or not
				allAccounts.forEach((account) => {
					account.showingOptions = false;
				});
				setAccounts(allAccounts);
			})
			.catch((error) => console.error(`Error: ${error}`));
	};

	// This occurs when the user has selected an account
	// it displays additional options (deposit, withdraw, transfer)
	const showAdditionalActions = (acctNum) => {
		console.log("showAdditionalActions: ", acctNum);
		if (acctNum == store.getState().accountReducer.currentAccountId) {
			console.log("Additional Actions should be shown for acct ", acctNum);
			return <AdditionalActions />;
		}
	};

	// When selected, this updates the state
	// Selected account is showing options, hides old one
	// acctNum = specified account (clicked or unclicked)
	// isToggled = boolean, if selected or deselected
	function updateAccountDisplay(acctNum, isToggled) {
		console.log("updating " + acctNum + " with: " + isToggled);
		let modifiedAccounts = accounts;
		// find the selected account and set its displayoptions to none
		for (let i = 0; i < modifiedAccounts.length; i++) {
			let account = modifiedAccounts[i];
			if (account.accountId === acctNum) {
				account.showingOptions = isToggled;
			}
		}
		// Update the account state
		setAccounts(modifiedAccounts);
	}

	const handleAccountClick = (event, props, data, triggerEvent) => {
		// if an account had previously been selected, hide the additional options
		const currentlySelectedAccount =
			store.getState().accountReducer.currentAccountId;
		if (currentlySelectedAccount) {
			// hide dropdown from previously selected account
			//console.log("An id already existed:", currentlySelectedAccount);
			console.log("updating to show account: ", event.currentTarget.id);
			updateAccountDisplay(currentlySelectedAccount, false);
		}
		// TODO route to `Transaction` page
		store.dispatch({
			type: "UPDATE_CURRENT_ACCOUNT_ID",
			payload: event.currentTarget.id,
		});

		// Show additional options for the currently selected account
		updateAccountDisplay(event.currentTarget.id, true);
	};

	const content = accounts.map((account) => {
		return (
			<div
				className="account_item"
				key={account.accountId}
				id={account.accountId}
				onClick={handleAccountClick}
			>
				<div className="account_name">
					<p>
						{account.name} (***{account.accountId.toString().slice(-4)})
					</p>
				</div>
				<div className="account_item_info">
					<div className="account_type">
						<p>{account.type}</p>
					</div>
					<div className="account_balance">
						<CurrencyFormat
							value={account.balance}
							displayType={"text"}
							thousandSeparator={true}
							prefix={"$"}
						/>
					</div>
				</div>
				{showAdditionalActions(account.accountId)}
			</div>
		);
	});

	return (
		<>
			<div className="account_list">{content}</div>
		</>
	);
}

export default AccountList;
