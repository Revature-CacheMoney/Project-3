/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import { useState } from "react";
import TransactionTable from "./TransactionTable";

function TransactionFilter(props) {
    const [filter, setFilter] = useState("");

    const changeFilter = (event) => {
        setFilter(event.target.value);
    }

    return (
        <>
            <TransactionTable filter={filter} />

            <select id="amountFilter" onChange={changeFilter} value={this.state.value}>
                <option value="NONE">Credit/Debit</option>
                <option value="CREDIT">Credit</option>
                <option value="DEBIT">Debit</option>
            </select>
        </>
    );

}

export default TransactionFilter;
