import React from "react";
import AccountObject from "./AccountObject.js";

function MainPageView(props) {
	let accountPreview = [];

	for (let i = 0; i < 5; i++) {
		accountPreview.push(<AccountObject key={"account" + i} />);

		console.log(accountPreview);

		//we want the component so we use the self closing tag for Account
	}

	return (
		<div id="main-page-container">
			<div className="headerContainer">
				<h1> Account </h1>
				<hr />

				<h2> CACHE FINANCE</h2>

				{accountPreview}

				<hr />

				<h2> CACHE RECREATION</h2>
				<AccountObject />
				<hr />
			</div>
		</div>
	);
}

export default MainPageView;
