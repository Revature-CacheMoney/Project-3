/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
import axios from "axios";
import config from "../../config";
import store from "../../store/Store";

//Implementing patch to fix Axios DDoS vulnerability.
import rateLimit from 'axios-rate-limit';


function Withdraw(props) {
	// use rate limit to prevent DDoS attacks
	const http = rateLimit(axios.create(), { maxRequests: 2, perMilliseconds: 1000, maxRPS: 2 })
	
	// post withdraw transaction
	const postWithdraw = (transaction) => {
		axios
			.post(`${config.url}accounts/withdraw`, transaction, {
				headers: {
					token: store.getState().userReducer.token,
					userId: store.getState().userReducer.userId,
				},
			})
			.catch((error) => console.error(`Error: ${error}`));
	};

	// what the submit button should do
	const handleSubmit = (event) => {
		// prevent page reloading
		event.preventDefault();

		// create the withdraw payload
		let withdraw = {
			account: {
				accountId: store.getState().accountReducer.currentAccountId,
			},
			transactionAmount: event.target.transactionAmount.value,
		};

		// perform the post
		postWithdraw(withdraw);
		// maybe do this in/after the api call
		// hacky workaround to try forcing the accounts list to update
		props.doTransactionDone(Date.now());
	};

	return (
		<div className="withdraw-outer-container">
			<div className="withdraw-inner-container">
				<div className="withdraw-form">
					<p className="withdraw-form-header">Withdraw</p>

					<form id="withdraw-inner-form" onSubmit={handleSubmit}>
						<div className="withdraw-amount">
							<label>Amount</label>
							<input
								type="number"
								min="0.01"
								step="0.01"
								id="withdraw-input"
								name="transactionAmount"
							/>
						</div>

						<button type="submit">Submit</button>
					</form>
				</div>
			</div>
		</div>
	);
}

export default Withdraw;
