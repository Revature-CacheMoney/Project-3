import React from "react";

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
<<<<<<< HEAD
					id="send-request"
=======
					id="transfer"
>>>>>>> 1f15d37228863160cf0be74c4c1590781d021101
				>
					Send/Request Money
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
