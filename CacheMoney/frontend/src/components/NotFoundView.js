// The NotFound component should be displayed if an invalid URL is entered
import { Link } from "react-router-dom";
import { useDarkMode } from "./style/useDarkMode";
import {ThemeProvider} from "styled-components";
import { GlobalStyles } from "../components/style/GlobalStyles";
import Toggle from "./style/Toggle";
import { lightTheme, darkTheme } from "../components/style/Themes";


// ie. no defined route
function NotFoundView() {

	const [theme, themeToggler, mountedComponent] = useDarkMode();
	const themeMode = theme === "light" ? lightTheme : darkTheme;

	if(!mountedComponent) return <div />

	return (
		<ThemeProvider theme={themeMode}>
			<>
				<GlobalStyles/>
				<div className="not-found-outer-container">
					<div className="not-found-inner-container">
						<div className="not-found-box">
							<h1 className="not-found-header">404 not found!</h1>
							<h3 className="not-found-description">It looks like the page you were looking for could not be found</h3>
							<Link to="/" className="not-found-link">
								<button className="not-found-home-button">
									<h3 className="not-found-home-text">Home</h3>
								</button>
							</Link>
							<h3 className="not-found-home-description">Click here to redirect to the home page</h3>
						</div>
					</div>
				</div>
			</>
		</ ThemeProvider>
	)
}

export default NotFoundView;
