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

.login-outer-container {
	background-image: ${({ theme }) => theme.moneyBackground};
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
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
	background-image: ${({ theme }) => theme.moneyBackground};
	background-size: 75%;
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

#splash-outer-container {
	background-image: ${({ theme }) => theme.splashBackground};
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
}

#splash-inner-container {
	background-color: ${({ theme }) => theme.bread};
}

#splash-mode-button {
	color: ${({ theme }) => theme.backlight};
	background-color: ${({ theme }) => theme.text};
	border: 3px solid ${({ theme }) => theme.backlight};
}

#splash-mode-button:hover {
	background-color: ${({ theme }) => theme.backlight};
	color: ${({ theme }) => theme.text};
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
	background-color: ${({ theme }) => theme.text};
	color: ${({ theme }) => theme.backlight};
}

#splash-mode-button:hover {
	color: ${({ theme }) => theme.text};
	background-color: ${({ theme }) => theme.backlight};
}

.benjamin button {
	color: ${({ theme }) => theme.backlight};
	background-color: ${({ theme }) => theme.text};
	border: 5px solid ${({ theme }) => theme.backlight};
}

.benjamin button:hover {
	color: ${({ theme }) => theme.text};
	background-color: ${({ theme }) => theme.backlight};
}

.footer {
	background-color: ${({ theme }) => theme.bread};
}

#footer-logo {
	width: 250px;
	height: 50px;
	background-image: ${({ theme }) => theme.cacheMoney};
	background-position: center;
	background-size: contain;
	background-repeat: no-repeat;
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

.main-page-content {
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

.chatbot-box {
	background-color: ${({ theme }) => theme.text};
	color: ${({ theme }) => theme.shadow};
}

.react-chatbot-kit-chat-container {
	background-color: ${({ theme }) => theme.bread};
}

.react-chatbot-kit-chat-bot-message-container {
	color: ${({ theme }) => theme.text};
	text-align: center;
	font-weight: 700;
}

.react-chatbot-kit-chat-header {
	background-color: ${({ theme }) => theme.text};
	color: ${({ theme }) => theme.shadow};
}

.option-button {
	color: ${({ theme }) => theme.backlight};
	background-color: ${({ theme }) => theme.text};
}

.option-button:hover {
	color: ${({ theme }) => theme.text};
	background-color: ${({ theme }) => theme.backlight};
  }

  .react-chatbot-kit-chat-input {
	color: ${({ theme }) => theme.bread};
	background-color: ${({ theme }) => theme.text};
  }
  
  .react-chatbot-kit-chat-input:focus {
	color: ${({ theme }) => theme.text};
	background-color: ${({ theme }) => theme.shadow};
  }
  
  .react-chatbot-kit-chat-input::placeholder {
	color: ${({ theme }) => theme.bread};
  }
  
  .react-chatbot-kit-chat-input::placeholder:focus {
	color: ${({ theme }) => theme.text};
  }

  .react-chatbot-kit-chat-btn-send {
	background-color: ${({ theme }) => theme.highlight};
  }

  .react-chatbot-kit-chat-btn-send:hover {
	background-color: ${({ theme }) => theme.ultrahighlight};
  }

  .read-more-link {
	color: ${({ theme }) => theme.bread};
	background-color: ${({ theme }) => theme.text};
  }

  .read-more-link:hover {
	color: ${({ theme }) => theme.highlight};
  }

  #popup-2 {
	background: ${({ theme }) => theme.shadow};
  }

  #popup-2 a {
	  color: ${({ theme }) => theme.backlight};
	  font-weight: 900;
  }

  #popup-2 a:hover {
	  color: ${({ theme }) => theme.text}
  }

  [role=tooltip].popup-content {
	  box-shadow: 0 0 10px rgb(0 0 0 / 50%) !important;
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