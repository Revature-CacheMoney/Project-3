import React from "react";

// 		(From Gideon) it turns out that using "<a href="#">" just links to the top of the current page, and shouldn't
// be vulnerable to URL injection (not 100% sure on that, though). But this does mean that this file shouldn't need any
// immediete patches.

function NavBar(props) {
	// Make sure to add additional cases to MainPageView to handle any routing changes/new links
	return (
		<div className="navigation-bar">
			<span
				className="navigation-link"
				onClick={props.handleClick}
				id="account-overview"
			>
				Accounts
			</span>

			<a href="#">
				<span
					className="navigation-link"
					onClick={props.handleClick}
					id="create-account"
				>
					Create Account
				</span>
			</a>
			<a href="#">
				<span
					className="navigation-link"
					onClick={props.handleClick}
					id="investment-portfolio"
				>
					Investment Porfolio
				</span>
			</a>
			<a href="#">
				<span
					className="navigation-link"
					onClick={props.handleClick}
					id="external-transfer"
				>
					Send Money
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
