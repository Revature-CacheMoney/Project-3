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

    // var select = document.getElementById('selectFilter');
    // var value = select.options[select.selectedIndex].value;
    // console.log(value);

    return (
        <>
            <TransactionTable filter={filter} />

            <select id="selectFilter" onChange={changeFilter}>
                <option value="NONE" selected="selected">Credit/Debit</option>
                <option value="CREDIT">Credit</option>
                <option value="DEBIT">Debit</option>
            </select>
        </>
    );

}

export default TransactionFilter;
