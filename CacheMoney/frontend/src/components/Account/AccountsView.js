import React, { useState } from "react";

// The Account component should hold multiple transactions related to this account.
// Also may contain other related account info (name, account number) and possibly link to do
// transfers as well (or another parent component?)
function AccountsView(props) {
    // local transaction state
    const [accounts, getAccounts] = useState([]);

    // url
    const url = "http://localhost:8080/";

	return (
		<>
			
		</>
	);
}

export default AccountsView;
