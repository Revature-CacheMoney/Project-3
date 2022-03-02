import React, { useState } from "react";
import AccountsView from "./Account/AccountsView";
import Navigation from "./NavBar";

function MainPageView(props) {
  //let accountPreview = [];
//   let [accountPreview, setAccountPreview] = useState([]);
  let [accountPreview, setAccountPreview] = useState(["Checking", "Savings", "California Move Stash", "Wu-tang"]);

  let showAccountPreview = accountPreview.map((account) => {
	  return (<li className="list-accounts">{account}</li>);
  })

//   for (let i = 0; i < accountPreview.length; i++){
// 	  return accountPreview[i];
//   }
//   for (let i = 0; i < 5; i++) {
//     accountPreview.push(<AccountsView key={"account" + i} />);

//     console.log(accountPreview);


  return (
    <div id="main-page">
      <div className="headerContainer">
		  <Navigation />
        <h1> Accounts </h1>
        <hr />

        <h2> CACHE FINANCE</h2>

        {showAccountPreview}
		{/* {accountPreview} */}

        <hr />

        <h2> CACHE RECREATION</h2>
        <AccountsView />
        <hr />
      </div>
    </div>
  );
}

export default MainPageView;
