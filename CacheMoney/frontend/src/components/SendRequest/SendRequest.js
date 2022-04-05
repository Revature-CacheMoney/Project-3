/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
import axios from "axios";
import config from "../../config";
import store from "../../store/Store";
import SendRequestSelection from "./SendRequestSelection";

function SendRequest(props) {
    // post transfer transaction
    const postTransfer = (transaction) => {
        axios
            .post(`${config.url}transfer`, transaction, {
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
        // hacky workaround to try forcing the accounts list to update
        props.doTransactionDone(Date.now());
    };

    return (
        <div className="create-account-outer-container">
            <div className="create-account-inner-container">
                <div className="account_create_form">
                    <p className="account_create_form_header">Money Transfer</p>

                    <form>
                        <div className="transfer-from-account">
                            <label>From</label>
                            <SendRequestSelection whichAccount="SOURCE"></SendRequestSelection>
                        </div>

                        <div className="transfer-to-account">
                            <label>Recipient's Account ID:</label>
                            <input type="number"/><br></br>
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

                    </form>

                    <button
                        className="account-create_submit_button"
                        type="button"
                        name="submit"
                        id="create-new-account"
                        onClick={handleSubmit}
                    >
                        Submit
                    </button>

                </div>
            </div>
        </div>
    );
}

export default SendRequest;
