import React from "react";
import Navigation from "./NavBar.jsx";
import Footer from "./Footer.js";
import AccountList from "./Account/AccountList.js";
import userStore from "../store/Store.js";
import NavBar from "./NavBar.js";
import { useNavigate } from "react-router-dom";
import { useDarkMode } from "./style/useDarkMode";
import {ThemeProvider} from "styled-components";
import { GlobalStyles } from "../components/style/GlobalStyles";
import Toggle from "./style/Toggle";
import { lightTheme, darkTheme } from "../components/style/Themes";

function MainPageView2() {
	const navigate = useNavigate();
	const userData = userStore.getState().userReducer;
	console.log("Data store: ", userData);
	//const username = "REVMAN3076";
	console.log("Name: ", userData.firstName);

	const handleLogout = (event) => {
		// event.preventDefault;

		userStore.dispatch({
			type: "LOGOUT_USER",
			payload: ""
		});

		navigate("/");
	}

	const [theme, themeToggler, mountedComponent] = useDarkMode();
	const themeMode = theme === "light" ? lightTheme : darkTheme;

	if(!mountedComponent) return <div />

	return (
		<ThemeProvider theme={themeMode}>
			<>
				<GlobalStyles/>
				<div className="main-page-container container-view">
					<div className="header">
						<div className="header-welcome-box">
							Welcome, <br />
							<span className="header-username">
								{userData.firstName} {userData.lastName}
							</span>
						</div>
						<div className="main-upper-buttons">
							<a href="#">
								<button id="logout-button" onClick={handleLogout}> Log Out</button>
							</a>
							<Toggle id="main-theme-button" theme={theme} toggleTheme={themeToggler} />
						</div>
					</div>

					{/* <Navigation /> */}
					<NavBar />

					<div className="main-page-content">
						{/******* Insert body content here ********/}
						<AccountList />
					</div>

					<Footer />
				</div>
			</>
		</ ThemeProvider>
	);
}

export default MainPageView2;
