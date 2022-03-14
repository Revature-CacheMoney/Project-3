/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
import axios from "axios";
import { useState } from "react";
import config from "../../config";
import store from "../../store/Store";

function Withdraw(props) {
    // local formData state
    const [formData, setFormData] = useState({
        account: {
            accountId: store.getState().accountReducer.currentAccountId
        },
        transactionAmount: ""
    });

    // retrieve the url from the config
    const url = config.url;

    // post withdraw transaction
    const postWithdraw = (transaction) => {
        axios.post(`${url}accounts/withdraw`, transaction, {
            headers: {
                token: store.getState().userReducer.token,
                userId: store.getState().userReducer.userId
            }
        })
            .catch(error => console.error(`Error: ${error}`));
    }

    // updates form data when form changes
    const handleChange = (event) => {
        event.preventDefault();
        setFormData({ ...formData, [event.target.name]: event.target.value });
    }

    // what the submit button should do
    const handleSubmit = () => {
        postWithdraw(formData);
    }

    return (
        <div className="withdraw-outer-container">
            <div className="withdraw-inner-container">
                <div className="withdraw-form">
                    <p className="withdraw-form-header">Withdraw</p>

                    <form>
                        <div className="withdraw-amount">
                            <label>Amount</label>
                            <input type="number" min="0.00" step="0.01" id="withdraw-input" name="transactionAmount" onChange={handleChange} />
                        </div>
                    </form>

                    <button className="withdraw-submit-button" type="button" name="submit" onClick={handleSubmit}>Submit</button>
                </div>
            </div>
        </div>
    );
}

export default Withdraw;