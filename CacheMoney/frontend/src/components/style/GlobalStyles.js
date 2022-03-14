import { createGlobalStyle } from "styled-components";
export const GlobalStyles = createGlobalStyle`
body {
	color: ${({ theme }) => theme.text};
}

::-webkit-scrollbar {
  width: 15px;
}

::-webkit-scrollbar-track {
  border-radius: 10px;
}
 
::-webkit-scrollbar-thumb {
  background: ${({ theme }) => theme.backlight}; 
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: ${({ theme }) => theme.ultrahighlight}; 
}

a {
	color: ${({ theme }) => theme.highlight};
}

.logo-bigger {
	text-shadow: -2px 4px 0px ${({ theme }) => theme.backlight};
}

.main-container-content {
	background-color: ${({ theme }) => theme.bread};

}

.password-error {
	border: 1px solid ${({ theme }) => theme.error};
}

.password-ok {
	border: 1px solid ${({ theme }) => theme.greyness};
}

.login-inner-container {
	background-color: ${({ theme }) => theme.bread};
}

.login-white-box {
	color: ${({ theme }) => theme.bread};
	background-color: ${({ theme }) => theme.text};
}

.login {
	background-color: ${({ theme }) => theme.highlight};
	color: ${({ theme }) => theme.text};
}

.login:hover {
	text-shadow: 0px 0px 5px ${({ theme }) => theme.greyness};
}

#login-theme-button {
	color: ${({ theme }) => theme.backlight};
	background-color: ${({ theme }) => theme.text};
	border: 3px solid ${({ theme }) => theme.backlight};
}

#login-theme-button:hover {
	background-color: ${({ theme }) => theme.backlight};
	color: ${({ theme }) => theme.text};
}

.login-outer-container input[type="text"] {
	border: 2px solid ${({ theme }) => theme.bread};
	box-shadow: -4px 4px 0px ${({ theme }) => theme.gentle};
}

#login-error-box {
	color: ${({ theme }) => theme.error};
}

#registration-error {
    color: ${({ theme }) => theme.error};
}

::selection {
	background: ${({ theme }) => theme.highlight};
	color: ${({ theme }) => theme.text};
}

input[type="text"]:focus {
	background-color: ${({ theme }) => theme.gentle};
	color: ${({ theme }) => theme.text};
}

input[type="text"] {
	color: ${({ theme }) => theme.greyness};
}

.err-desc {
	color: ${({ theme }) => theme.error};
}

#splash-inner-container {
	background-color: ${({ theme }) => theme.bread};
}

#register-here {
	color: ${({ theme }) => theme.backlight};
}

#register-here:hover {
	color: ${({ theme }) => theme.ultrahighlight};
}

#register-theme-button {
	color: ${({ theme }) => theme.backlight};
	background-color: ${({ theme }) => theme.text};
	border: 3px solid ${({ theme }) => theme.backlight};
}

#register-theme-button:hover {
	background-color: ${({ theme }) => theme.backlight};
	color: ${({ theme }) => theme.text};
}

#splash-button {
	background-color: ${({ theme }) => theme.text};
	box-shadow: -5px 5px 0px 1px ${({ theme }) => theme.backlight};
	color: ${({ theme }) => theme.shadow};
}

#splash-button:hover {
	background-color: ${({ theme }) => theme.backlight};
	color: ${({ theme }) => theme.text};
}

#splash-mode-button {
	border: 5px solid ${({ theme }) => theme.backlight};
	color: ${({ theme }) => theme.backlight};
}

#splash-mode-button:hover {
	color: ${({ theme }) => theme.text};
	background-color: ${({ theme }) => theme.backlight};
}

.footer {
	background-color: ${({ theme }) => theme.bread};
}

.nav-bar {
	color: ${({ theme }) => theme.greyness};
}

.breadcrumb-item > a {
	color: ${({ theme }) => theme.greyness};
}

.account-type-container {
	color: ${({ theme }) => theme.greyness};
}

.transfer-container {
	color: ${({ theme }) => theme.greyness};
}

.main-page-container {
	background-color: ${({ theme }) => theme.background}
}

.header {
	background-color: ${({ theme }) => theme.bread};
}

.main-content-inner {
	background-color: ${({ theme }) => theme.background};
}

.header-username {
	text-shadow: -1px 2px 0px ${({ theme }) => theme.highlight};
}

.navigation-bar {
	background-color: ${({ theme }) => theme.bread};
	border-bottom: 10px solid ${({ theme }) => theme.shadow};
}

.navigation-bar a {
	color: ${({ theme }) => theme.text};
}

#logout-button {
	color: ${({ theme }) => theme.greyness};
	background-color: ${({ theme }) => theme.text};
	box-shadow: -4px 4px 0px ${({ theme }) => theme.shadow};
}

#logout-button:hover {
	color: ${({ theme }) => theme.text};
	background-color: ${({ theme }) => theme.highlight};}

#main-theme-button {
	color: ${({ theme }) => theme.greyness};
	background-color: ${({ theme }) => theme.text};
	box-shadow: -4px 4px 0px ${({ theme }) => theme.shadow};
}

#main-theme-button:hover {
	color: ${({ theme }) => theme.text};
	background-color: ${({ theme }) => theme.highlight};}
}

.account_create_form {
    color: ${({ theme }) => theme.greyness};
    border: 1px solid ${({ theme }) => theme.greyness};

}

.account_item {
	border: 2px solid ${({ theme }) => theme.shadow};
}

.account_name {
	background-color: ${({ theme }) => theme.shadow};
}

.account_type {
	border: 1px solid ${({ theme }) => theme.shadow};
	border-bottom-left-radius: 8px;
	background-color: ${({ theme }) => theme.gentle};
}

.account_balance {
	border-top: 1px solid ${({ theme }) => theme.shadow};
	background-color: ${({ theme }) => theme.gentle};
}

.not-found-inner-container {
    background-color: ${({ theme }) => theme.bread};
}

.not-found-home-button {
    background-color: ${({ theme }) => theme.text};
    color: ${({ theme }) => theme.highlight};
}

.not-found-home-button:hover {
    box-shadow: -5px 5px ${({ theme }) => theme.bread};
    color: ${({ theme }) => theme.gentle};
}

.not-found-home-text {
    text-shadow: -2px 2px ${({ theme }) => theme.shadow};
}

@media only screen and (max-width: 1080px) {
	#splash-outer-container button,
	#splash-outer-container input[type="submit"] {
		background-color: ${({ theme }) => theme.text};
		box-shadow: -5px 5px 0px 1px ${({ theme }) => theme.highlight};
		color: ${({ theme }) => theme.shadow}; 
	}
}
`