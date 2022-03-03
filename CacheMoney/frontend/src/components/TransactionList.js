import { useEffect, useState } from "react";
import axios from 'axios';

function TransactionList(props) {
    // local transaction state
    const [transactions, getTransactions] = useState([]);

    // url
    const url = "http://localhost:8080/";

    // effect hook
    useEffect(() => {
        getAllTransactions();
    }, [])

    //TODO need to consider user id somewhere
    const getAllTransactions = () => {
        axios.get(`${url}transactions`)
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
                    <td>{transaction.transaction_date}</td>
                    <td>{transaction.description}</td>
                    <td>{transaction.transaction_amount}</td>
                    <td>{transaction.ending_balance}</td>
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