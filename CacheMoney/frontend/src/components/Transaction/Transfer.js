/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
import axios from "axios";
import config from "../../config";
import store from "../../store/Store";
import TransferSelection from "./TransferSelection";

//TODO finish this
function Transfer(props) {
	// post transfer transaction
	const postTransfer = (transaction) => {
		axios
			.post(`${config.url}accounts/transfer`, transaction, {
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

		// create the transfer payload
		let transfer = {
			sourceAccountId: store.getState().transferReducer.sourceAccountId,
			destinationAccountId:
				store.getState().transferReducer.destinationAccountId,
			transaction: {
				description: event.target.description.value,
				transactionAmount: event.target.transactionAmount.value,
			},
		};

		// perform the post
		postTransfer(transfer);
		// maybe do this in/after the api call
		props.doTransactionDone(Date.now());
	};

	return (
		<div className="transfer-outer-container">
			<div className="transfer-inner-container">
				<div className="transfer-form">
					<p className="transfer-form-header">Transfer</p>

					<form onSubmit={handleSubmit}>
						<div className="transfer-from-account">
							<label>FROM</label>
							<TransferSelection whichAccount="SOURCE"></TransferSelection>
						</div>

						<div className="transfer-to-account">
							<label>TO</label>
							<TransferSelection whichAccount="DESTINATION"></TransferSelection>
						</div>

						<div className="transfer-amount">
							<label>Amount</label>
							<input
								type="number"
								min="0.00"
								step="0.01"
								id="transfer-input"
								name="transactionAmount"
							/>
						</div>

						<div className="transfer-description">
							<label>Description</label>
							<input type="text" id="transfer-description" name="description" />
						</div>

						<button type="submit">Submit</button>
					</form>

					{/* <button className="transfer-submit-button" type="button" name="submit" onClick={handleSubmit}>Submit</button> */}
				</div>
			</div>
		</div>
	);
}

export default Transfer;
