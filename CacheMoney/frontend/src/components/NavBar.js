import React from "react";
import config from "../config";
import store from "../store/Store";
import Notification from "./Notification";
import {useState} from "react";
import axios from "axios";


function NavBar(props) {
	// Make sure to add additional cases to MainPageView to handle any routing changes/new links
	const [notificationDisplay, setNotificationDisplay] = useState('none');
	// @author Max Hilken, Mika Nelson, Cullen Kuch
	async function toggleNotificationDisplay(){
		var user;
		
		if (notificationDisplay == 'none'){
			setNotificationDisplay('block')
		} else {
			setNotificationDisplay('none')

			await axios
			.get(`${config.url}users/`, {
				headers: {
					token: store.getState().userReducer.token,
					userId: store.getState().userReducer.userId,
				},
			})
			.then((response) => {
				user = response.data;
				console.log(user);
			})
			.catch((error) => console.error(`Error: ${error}`));

			await axios
			.put(`${config.url}notifications/update`, user)
			
			.catch((error) => console.error(`Error: ${error}`));
		}
	}

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
			<div className="Notification">
				<a href="#">
					<span
						className="navigation-link"
						onClick={() => toggleNotificationDisplay()}
						id="Notification"
					>
						Notifications
					</span>
					<span style={{display: notificationDisplay}}>
						<Notification />
					</span>
				</a>
			</div>
		</div>
	);
}
export default NavBar;