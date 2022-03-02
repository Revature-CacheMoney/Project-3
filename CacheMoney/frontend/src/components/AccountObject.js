import React from "react";
import Transaction from "./Transaction.js";
import userStore from "../store/Store";

function AccountObject(props) {
	//transactions should probably be initialized in the store to []
	//let transactions = userStore.getState().accounts.transactions;

	// hopefully the acct is passed its own acct ID or some identifier to help id transaction keys

	let acctNumber = props.acctID ? props.acctID : 1;

	const dummyTransactions = [
		{
			date: "3/2/22",
			description: "Made money",
			type: "credit",
			amount: 1.0,
		},
		{
			date: "3/3/22",
			description: "Bought a house",
			type: "debit",
			amount: -600000.0,
		},
		{
			date: "3/4/22",
			description: "Went to McD's",
			type: "debit",
			amount: -9.0,
		},
	];
	// thought - should amount hold negative values?
	// if it does, does "debit/credit" need to be saved?
	// what about transfers?

	const transactionComponents = () => {
		let transactions = [];
		for (let i = 0; i < dummyTransactions.length; i++) {
			transactions.append(
				<Transaction
					data={dummyTransactions[i]}
					key={"acct" + acctNumber + "trans" + i}
				/>
			);
		}
		return transactions;
	};
	return (
		<>
			<table>{transactionComponents}</table>
		</>
	);
}

export default AccountObject;
