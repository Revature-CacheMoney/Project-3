/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import axios from "axios";
import React, { useEffect, useState } from "react";
import config from "../../config.js";
import store from "../../store/Store.js";
import "../../css/Account.css"

function AccountsList(props) {
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
                    <>
                        <div className="account_item">
                            <div className="account_name">
                                {accounts.name} {accounts.account_id}
                            </div>
                            <div className="account_type">
                                {accounts.type}
                            </div>
                            <div className="account_balance">
                                {accounts.balance}
                            </div>
                        </div>
                    </>
                )
            }
        )

    return (
        <>

        </>
    );
}

export default AccountsList;
