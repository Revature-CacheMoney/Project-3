/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import { useEffect, useState } from 'react';
import axios from 'axios';
import store from '../store/Store';

function TransactionList(props) {
    // local transaction state
    const [transactions, getTransactions] = useState([]);

    // url
    const url = "http://localhost:8080/";

    // effect hook
    useEffect(() => {
        getAllTransactions();
    }, [])

    const getAllTransactions = () => {
        //TODO path uri due to change
        axios.get(`${url}accounts/transactions/${store.getState().currentAccountId}`)
            .then((response) => {
                const allTransactions = response.data;
                getTransactions(allTransactions);
            })
            .catch(error => console.error(`Error: ${error}`));
    }

    const content = transactions.map(
        (transaction) => {
            return (
                <tr>
                    <th id="header_date">Date</th>
                    <th id="header_description">Description</th>
                    <th id="header_amount">Debit/Credit</th>
                    <th id="header_balance">Balance</th>
                </tr>
            );
        }
    );

    return (
        <>
            <div className="transaction_container">
                <table className="transaction_table">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Description</th>
                            <th>Debit/Credit</th>
                            <th>Balance</th>
                        </tr>
                    </thead>
                    {content}
                </table>
            </div>
        </>
    );
}

export default TransactionList;