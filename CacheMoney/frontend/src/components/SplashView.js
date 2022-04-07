import React from "react";
import Chatbot from "react-chatbot-kit";
import chatbotConfig from "../chatbot/chatbotConfig";
import MessageParser from "../chatbot/MessageParser";
import ActionProvider from "../chatbot/ActionProvider";
import { useDarkMode } from "./style/useDarkMode";
import { ThemeProvider } from "styled-components";
import { GlobalStyles } from "../components/style/GlobalStyles";
import Toggle from "./style/Toggle";
import { lightTheme, darkTheme } from "../components/style/Themes";
import Popup from "reactjs-popup";
import "reactjs-popup/dist/index.css";

// The splash screen is the first view the user will see when using the app.
// It prompts the user to login or register.

/*
function validateURL(url) {
	const parsed = new URL(url)
	return ['https:', 'http:'].includes(parsed.protocol)
}
*/
// While this file does use <a> tags with href, I do not believe this is vulnerable to URL injection. Since all
// of the href statements point to hard-coded text and not a variable, so an attacker shouldn't be able to modify it.

function SplashView() {
  const [theme, themeToggler, mountedComponent] = useDarkMode();
  const themeMode = theme === "light" ? lightTheme : darkTheme;

  if (!mountedComponent) return <div />;
  return (
    <ThemeProvider theme={themeMode}>
      <>
        <GlobalStyles />
        <div id="splash-outer-container" className="container-view">
          <div className="benjamin">
            <Popup trigger={<button>Chat</button>} position="center">
              <div className="benjamin-container">
                <Chatbot
                  config={chatbotConfig}
                  actionProvider={ActionProvider}
                  messageParser={MessageParser}
                />
              </div>
            </Popup>
          </div>
          <div id="splash-mode-container">
            <Toggle
              theme={theme}
              id="splash-mode-button"
              toggleTheme={themeToggler}
            />
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
                Not currently a user?{" "}
                <a href="./register" id="register-here">
                  Register here
                </a>
              </span>
            </div>
          </div>
        </div>
      </>
    </ThemeProvider>
  );
}

export default SplashView;
