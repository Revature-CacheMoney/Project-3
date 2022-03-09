import React from "react";

function NavBar(props) {
	return (
		<div className="navigation-bar">
				<a href="/accounts">
					<span className="navigation-link">Accounts</span>
				</a>
				<a href="/accounts/create">
					<span className="navigation-link">Create Account</span>
				</a>
				<a href="/transfer">
					<span className="navigation-link">Transfer Money</span>
				</a>
				<div className="settings">
					<a href="#">
						<span className="navigation-link">Settings</span>
					</a>
				</div>
			</div>
	);
}
export default NavBar;
