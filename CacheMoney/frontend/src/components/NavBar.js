import React from "react";

function NavBar(props) {
	const testHover = (event) => {
		console.log("Hover event", event.target.id);
	};

	// Make sure to add additional cases to MainPageView to handle any routing changes/new links
	return (
		<div className="navigation-bar">
			{/*<a href="/accounts">*/}
			<a href="#">
				<span
					className="navigation-link"
					onClick={props.handleClick}
					id="account-overview"
				>
					Accounts
				</span>
			</a>
			<a href="#">
				{/*<a href="/accounts/create">*/}
				<span
					className="navigation-link"
					onClick={props.handleClick}
					id="create-account"
				>
					Create Account
				</span>
			</a>
			<a href="#">
				{/*<a href="/transfer">*/}
				<span
					className="navigation-link"
					onClick={props.handleClick}
					id="investment-portfolio"
				>
					Investment Porfolio
				</span>
			</a>
			<a href="#">
				{/*<a href="/transfer">*/}
				<span
					className="navigation-link"
					onClick={props.handleClick}
					id="rewards"
				>
					Rewards
				</span>
			</a>
			<div className="settings">
				<a href="#">
					<span
						className="navigation-link"
						onClick={props.handleClick}
						id="settings"
					>
						Settings
					</span>
				</a>
			</div>
		</div>
	);
}
export default NavBar;
