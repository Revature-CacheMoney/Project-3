/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
import axios from "axios";
import config from "../../config";
import store from "../../store/Store";

function Deposit(props) {
	// post deposit transaction
	const postDeposit = (transaction) => {
		axios
			.post(`${config.url}accounts/deposit`, transaction, {
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

		// create the deposit payload
		let deposit = {
			account: {
				accountId: store.getState().accountReducer.currentAccountId,
			},
			transactionAmount: event.target.transactionAmount.value,
		};

		// perform the post
		postDeposit(deposit);
		// maybe do this in/after the api call
		props.doTransactionDone(Date.now());
	};

	return (
		<div className="deposit-outer-container">
			<div className="deposit-inner-container">
				<div className="deposit-form">
					<p className="deposit-form-header">Deposit</p>

					<form id="deposit-inner-form" onSubmit={handleSubmit}>
						<div className="deposit-amount">
							<label>Amount</label>
							<input
								type="number"
								min="0.01"
								step="0.01"
								id="deposit-input"
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

export default Deposit;
