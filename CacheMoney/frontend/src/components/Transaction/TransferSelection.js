/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
// import axios from "axios";
// import { useEffect, useState } from "react";
// import config from "../../config";
// import store from "../../store/Store";
//
// function TransferSelection({ whichAccount }) {
// 	const [accountId, setAccountId] = useState();
// 	const [accounts, setAccounts] = useState([]);
//
// 	// update account selection locally & in parent component
// 	const changeAccount = (event) => {
// 		// update local state
// 		setAccountId(event.target.value);
//
// 		// submit the state to the store
// 		if (whichAccount === "SOURCE") {
// 			store.dispatch({
// 				type: "UPDATE_SOURCE_ACCOUNT_ID",
// 				payload: event.target.value,
// 			});
// 		} else if (whichAccount === "DESTINATION") {
// 			store.dispatch({
// 				type: "UPDATE_DESTINATION_ACCOUNT_ID",
// 				payload: event.target.value,
// 			});
// 		}
// 	};
//
// 	// effect hook
// 	useEffect(() => {
// 		// retrieve all user's accounts
// 		axios
// 			.get(`${config.url}users/accounts`, {
// 				headers: {
// 					token: store.getState().userReducer.token,
// 					userId: store.getState().userReducer.userId,
// 				},
// 			})
// 			.then((response) => {
// 				setAccounts(response.data);
// 			})
// 			.catch((error) => console.error(`Error: ${error}`));
// 	}, []);
//
// 	// map options from get call
// 	const options = accounts.map((account) => {
// 		return (
// 			<option key={account.accountId} value={account.accountId}>
// 				{account.name} (***{account.accountId.toString().slice(-4)})
// 			</option>
// 		);
// 	});
//
// 	return (
// 		<select id="selectAccount" onChange={changeAccount} value={accountId}>
// 			<option key="default">Select an account...</option>
// 			{options}
// 		</select>
// 	);
// }
//
// export default TransferSelection;
