import React from "react";

function NavBar(props) {
	const testHover = (event) => {
		console.log("Hover event", event.target.id);
	};

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
					id="transfer-money"
				>
					Transfer Money
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
