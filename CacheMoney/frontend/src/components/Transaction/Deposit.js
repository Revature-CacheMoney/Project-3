/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
import axios from "axios";
import { useState } from "react";
import config from "../../config";
import store from "../../store/Store";

function Deposit(props) {
    // local formData state
    const [formData, setFormData] = useState({
        account: {
            accountId: store.getState().accountReducer.currentAccountId
        },
        transactionAmount: ""
    });

    // retrieve the url from the config
    const url = config.url;

    // post deposit transaction
    const postDeposit = (transaction) => {
        axios.post(`${url}accounts/deposit`, transaction, {
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
        postDeposit(formData);
    }

    return (
        <div className="deposit-outer-container">
            <div className="deposit-inner-container">
                <div className="deposit-form">
                    <p className="deposit-form-header">Deposit</p>

                    <form>
                        <div className="deposit-amount">
                            <label>Amount</label>
                            <input type="number" min="0.00" step="0.01" id="deposit-input" name="transactionAmount" onChange={handleChange} />
                        </div>
                    </form>

                    <button className="deposit-submit-button" type="button" name="submit" onClick={handleSubmit}>Submit</button>
                </div>
            </div>
        </div>
    );
}

export default Deposit;