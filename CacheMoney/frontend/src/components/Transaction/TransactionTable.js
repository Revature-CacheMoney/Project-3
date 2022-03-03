/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import { useEffect, useState } from 'react';
import axios from 'axios';
import store from '../../store/Store';
import config from "../../config.js";

function TransactionTable(props) {
    // local transaction state
    const [transactions, getTransactions] = useState([]);

    // url
    const url = config.url;

    // default props
    TransactionTable.defaultProps = {
        /**
         * Expected values:
         *   NONE
         *   CREDIT
         *   DEBIT
         */
        filter: "NONE"
    }

    // effect hook
    useEffect(() => {
        getAllTransactions();
    })

    // retrieve all transactions based on user's account's id
    const getAllTransactions = () => {
        axios.get(`${url}accounts/transactions/${store.getState().currentAccountId}`)
            .then((response) => {
                const allTransactions = response.data;
                getTransactions(allTransactions);
            })
            .catch(error => console.error(`Error: ${error}`));
    }

    // map transactions from local state based on store
    const content = transactions
        .filter(transaction => {
            if (props.filter === "NONE") {
                console.log("filter: none");
                return true;
            }
            else if (props.filter === "CREDIT") {
                console.log("filter: credit");
                return transaction.transaction_amount > 0;
            }
            else if (props.filter === "DEBIT") {
                console.log("filter: debit");
                return transaction.transaction_amount < 0;
            }
            else {
                console.error("Invalid amount filter!");
                return false;
            }
        })
        .map(
            (transaction) => {
                return (
                    <tr key={transaction.transaction_id}>
                        <td>{transaction.transaction_date}</td>
                        <td>{transaction.description}</td>
                        <td>{transaction.transaction_amount}</td>
                        <td>{transaction.ending_balance}</td>
                    </tr>
                );
            }
        );

    // the html stuff
    return (
        <>
            <div className="transaction_container">
                <table className="transaction_table">
                    <thead>
                        <tr>
                            <th id="header_date">Date</th>
                            <th id="header_description">Description</th>
                            <th id="header_amount">Credit/Debit</th>
                            <th id="header_balance">Balance</th>
                        </tr>
                    </thead>
                    <tbody>
                        {content}
                    </tbody>
                </table>
            </div>
        </>
    );
}

export default TransactionTable;