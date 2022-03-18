import React from "react";

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
