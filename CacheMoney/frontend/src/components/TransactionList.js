import { useEffect, useState } from "react";
import axios from 'axios';

function TransactionList(props) {
    // local transaction state
    const [transactions, getTransactions] = useState(null);

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
                <div key={transaction.transaction_id}>
                    <h3>{transaction.description}</h3>

                    <p>{transaction.account_id}</p>

                    <p>{transaction.transaction_date}</p>

                    <p className="left_align">
                        {transaction.ending_balance}

                        <span>{transaction.transaction_amount}</span>
                    </p>
                </div>
            );
        }
    );

    return (
        <>
            <div className="transaction_container">
                {content}
            </div>
        </>
    );
}

export default TransactionList;