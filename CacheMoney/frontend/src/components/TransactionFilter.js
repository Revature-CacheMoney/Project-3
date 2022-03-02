/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import { useState } from "react";
import TransactionTable from "./TransactionTable";
import '../css/Transaction.css';

function TransactionFilter(props) {
    const [filter, setFilter] = useState("NONE");

    const changeFilter = (event) => {
        setFilter(event.target.value);
    }

    return (
        <>
            {/* drop-down filter selector menu */}
            <select id="selectFilter" onChange={changeFilter} value={filter}>
                <option value="NONE">Credit/Debit</option>
                <option value="CREDIT">Credit</option>
                <option value="DEBIT">Debit</option>
            </select>

            {/* accessing the transaction table component */}
            <TransactionTable filter={filter} />
        </>
    );

}

export default TransactionFilter;
