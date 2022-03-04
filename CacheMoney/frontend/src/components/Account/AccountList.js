/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import axios from "axios";
import React, { useEffect, useState } from "react";
import config from "../../config.js";
import store from "../../store/Store.js";
import "../../css/Account.css"

function AccountList(props) {
    // local transaction state
    const [accounts, getAccounts] = useState([]);

    // url
    const url = config.url;

    // effect hook
    useEffect(() => {
        getAllAccounts();
    })

    const getAllAccounts = () => {
        axios.get(`${url}users/accounts/${store.getState().userId}`)
            .then((response) => {
                const allAccounts = response.data;
                getAccounts(allAccounts);
            })
            .catch(error => console.error(`Error: ${error}`));
    }

    const content = accounts
        .map(
            (account) => {
                return (
                    <div className="account_item" key={account.accountId}>
                        <div className="account_name">
                            <p>SendHelp (***{account.accountId.toString().slice(-4)})</p>
                        </div>
                        <div className="account_item_info">
                            <div className="account_type">
                                <p>{account.type}</p>
                            </div>
                            <div className="account_balance">
                                <p>${account.balance}</p>
                            </div>
                        </div>
                    </div>
                )
            }
        )

    return (
        <>
            <div className="account_list">
                {content}
            </div>
        </>
    );
}

export default AccountList;