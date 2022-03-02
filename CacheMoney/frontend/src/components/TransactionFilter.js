/**
 * @Author Cody Gonsowski, Jeffrey Lor
 */

import TransactionTable from "./TransactionTable";

function TransactionFilter(props) {
    
    function amountFilter() {
        document.getElementById("amountFilter");
    }

    // onChange={}

    return (
        <>
            <TransactionTable fields="CREDIT"/>

            <select id="amountFilter">
                <option value="NONE">Credit/Debit</option>
                <option value="CREDIT">Credit</option>
                <option value="DEBIT">Debit</option>
            </select>
        </>
    );

}

export default TransactionFilter;
