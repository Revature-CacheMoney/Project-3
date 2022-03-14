/**
 * @author Cody Gonsowski & Jeffrey Lor
 */
import axios from "axios";
import { useEffect, useState } from "react";
import config from "../../config";
import store from "../../store/Store";

//TODO finish this
function TransferSelection({ updateAccounts }) {
    const [account, setAccount] = useState("");
    const [accounts, setAccounts] = useState([]);

    // update account selection locally & in parent component
    const changeAccount = (event) => {
        setAccount(event.target.value);
        updateAccounts(account);
    }

    // retrieve url from config
    const url = config.url;

    // effect hook
    useEffect(() => {
        getAccounts();
    })

    // get all accounts associated with the user
    const getAccounts = () => {
        axios.get(`${url}users/accounts`, {
            headers: {
                token: store.getState().userReducer.token,
                userId: store.getState().userReducer.userId
            }
        })
            .then((response) => {
                setAccounts(response.data);
            })
            .catch(error => console.error(`Error: ${error}`));
    }

    // map options from get call
    const options = accounts
        .map(
            (account) => {
                return (
                    <option key={account.accountId} value={account.accountId}>{account.name} (***{account.accountId.toString().slice(-4)})</option>
                )
            }
        )

    return (
        <select id="selectAccount" onChange={changeAccount} value={account}>
            {options}
        </select>
    );
}

export default TransferSelection;