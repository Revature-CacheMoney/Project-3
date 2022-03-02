import React from "react";
import Navigation from "./TestNav";

// The transfer component would hold the layout for making a transfer from one account to another.
// It should have two dropdowns (source and destination).  Under some conditions (ex. when user creates
// a new account or initiates transfer from an account link), the source dropdown box should be prepopulated
// with that account as the default option.

function TransferView(props) {
  return (
    <>
      <div className="transfer-container">
        <h4>I don't dude you have different account and you can transfer money between them here</h4>
        <h1>REVMAN3076</h1>
        <Navigation />
        <p>DO SOMETHING THAT DOES SOME TRANSFER STUFF</p>
		<p>MAYBE A LITTLE MORE TRANSFER STUFF OVER HERE</p>
      </div>
    </>
  );
}

export default TransferView;
