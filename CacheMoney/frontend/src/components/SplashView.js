import React, { useState, useEffect } from "react";
import { useDarkMode } from "./style/useDarkMode";
import {ThemeProvider} from "styled-components";
import { GlobalStyles } from "../components/style/GlobalStyles";
import Toggle from "./style/Toggle";
import { lightTheme, darkTheme } from "../components/style/Themes"

// The splash screen is the first view the user will see when using the app.
// It prompts the user to login or register.
function SplashView() {

	const [theme, themeToggler, mountedComponent] = useDarkMode();
	const themeMode = theme === "light" ? lightTheme : darkTheme;

	if(!mountedComponent) return <div />
	return (
		<ThemeProvider theme={themeMode}>
			<>
				<GlobalStyles/>
				<div id="splash-outer-container" className="container-view">
					<div id="splash-mode-container">
						<Toggle theme={theme} id="splash-mode-button" toggleTheme={themeToggler} />
					</div>
					<div id="splash-inner-container">
						<h1 className="logo-bigger">CacheMoney</h1>
						<div id="splash-right">
							<span id="welcome">WELCOME</span>
							<br />
							<a href="./signin">
								<button id="splash-button">Sign In</button>
							</a>
							<br />
							<span id="register">
								Not currently a user? <a href="./register" id="register-here">Register here</a>
							</span>
						</div>
					</div>
				</div>
			</>
		</ThemeProvider>
	);
}

export default SplashView;
