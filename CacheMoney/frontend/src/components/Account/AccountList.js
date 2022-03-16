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

	const handleAccountUpdate = () => {
		console.log("Accounts loaded", props.doAccountUpdate);
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
				setAccounts(allAccounts);
			})
			.catch((error) => console.error(`Error: ${error}`));
	};

	// watch if someone did a deposit, etc
	//store.subscribe(handleAccountUpdate);

	// effect hook
	useEffect(() => {
		console.log("useEffect");
		handleAccountUpdate();
	}, [props.doTitleUpdate]);
	//store.getState().accountReducer.someData

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
		// Do an account update just because
		handleAccountUpdate();
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
							decimalScale={2}
							fixedDecimalScale={true}
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
			return (
				<div className="no-account-message">
					You currently have no accounts. Select "Create Account" to get started
					today!
				</div>
			);
		} else {
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
