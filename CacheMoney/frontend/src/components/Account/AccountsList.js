/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import axios from "axios";
import React, { useEffect, useState } from "react";
import config from "../../config.js";
import store from "../../store/Store.js";

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
            // TODO finish this
            (account) => {
                return (
                    <></>
                )
            }
        )

    return (
        <>

        </>
    );
}

export default AccountsList;
