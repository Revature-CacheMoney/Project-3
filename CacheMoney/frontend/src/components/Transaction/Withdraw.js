/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
import axios from "axios";
import config from "../../config";
import store from "../../store/Store";

function Withdraw(props) {
    // post withdraw transaction
    const postWithdraw = (transaction) => {
        axios.post(`${config.url}accounts/withdraw`, transaction,
            {
                headers: {
                    token: store.getState().userReducer.token,
                    userId: store.getState().userReducer.userId
                }
            })
            .catch(error => console.error(`Error: ${error}`));
    }

    // what the submit button should do
    const handleSubmit = (event) => {
        // prevent page reloading
        event.preventDefault();

        // create the withdraw payload
        let withdraw = {
            account: {
                accountId: store.getState().accountReducer.currentAccountId
            },
            transactionAmount: event.target.transactionAmount.value
        }

        // perform the post
        postWithdraw(withdraw);
    }

    return (
        <div className="withdraw-outer-container">
            <div className="withdraw-inner-container">
                <div className="withdraw-form">
                    <p className="withdraw-form-header">Withdraw</p>

                    <form onSubmit={handleSubmit}>
                        <div className="withdraw-amount">
                            <label>Amount</label>
                            <input type="number" min="0.00" step="0.01" id="withdraw-input" name="transactionAmount" />
                        </div>

                        <button type="submit">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Withdraw;