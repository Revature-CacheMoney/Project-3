import React from "react";
//import userStore from "../store/Store";

function Transaction(props) {
	let { date, description, type, amount } = props.accounts;
	return (
		<>
			<tr>
				<td>{date}</td>
				<td>{description}</td>
				<td>{type}</td>
				<td>{amount}</td>
			</tr>
		</>
	);
}

export default Transaction;
