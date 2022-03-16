/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import axios from "axios";
import React, { useEffect, useState } from "react";
import config from "../../config.js";
import store from "../../store/Store.js";
import CurrencyFormat from "react-currency-format";

function AccountList(props) {
	const doTitleUpdate = props.doTitleUpdate;
	// local transaction state
	const [accounts, setAccounts] = useState([]);
	// setAccounts is the setter function
	// read state with accounts

	// effect hook
	useEffect(() => {
		// get all of a user's accounts
		axios
			.get(`${config.url}users/accounts`, {
				headers: {
					token: store.getState().userReducer.token,
					userId: store.getState().userReducer.userId,
				},
			})
			.then((response) => {
				const allAccounts = response.data;

				// Modifying the account object to also have whether displayed or not
				/*allAccounts.forEach((account) => {
					account.showingOptions = false;
				}); */
				setAccounts(allAccounts);
			})
			.catch((error) => console.error(`Error: ${error}`));
	}, []);
	//}, [accounts]);

	// When selected, this updates the state
	// Selected account is showing options, hides old one
	// acctNum = specified account (clicked or unclicked)
	// isToggled = boolean, if selected or deselected
	/*
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
	} */

	//event, props, data, triggerEvent
	const handleAccountClick = (event) => {
		// if an account had previously been selected, hide the additional options
		const currentlySelectedAccount = accounts.filter((account) => {
			if (account.accountId === parseInt(event.currentTarget.id)) {
				return account;
			} else {
				return null;
			}
		})[0];
		//store.getState().accountReducer.currentAccountId;

		store.dispatch({
			type: "UPDATE_CURRENT_ACCOUNT_ID",
			payload: event.currentTarget.id,
		});
		doTitleUpdate(currentlySelectedAccount);

		// Show additional options for the currently selected account
		//updateAccountDisplay(event.currentTarget.id, true);
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
			</div>
		);
	});

	const noAccountsMessage = () => {
		if (accounts.length === 0) {
			// This is the most hacky way to do this.  Sorry.

			try {
				document.getElementsByClassName(
					"account-container"
				)[0].style.flexDirection = "column";
			} catch {}
			return (
				<div className="no-account-message">
					You currently have no accounts. Select "Create Account" to get started
					today!
				</div>
			);
		} else {
			try {
				document.getElementsByClassName(
					"account-container"
				)[0].style.flexDirection = "row";
			} catch {}
			return;
		}
	};

	return (
		<>
			{noAccountsMessage()}
			<div className="account_list">{content}</div>
		</>
	);
}

export default AccountList;
