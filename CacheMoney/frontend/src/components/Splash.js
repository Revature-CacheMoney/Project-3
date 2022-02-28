import "../css/Splash.css";
import Title from "./Title.js";

function Splash() {
	return (
		<div id="splash-container">
			<Title />
			<div id="splash-right">
				<span id="welcome">WELCOME</span>
				<br />
				<a href="./signin">
					<button>Sign In</button>
				</a>
				<br />
				<span id="register">
					Not currently a user? <a href="./register">Register here</a>
				</span>
			</div>
		</div>
	);
}

export default Splash;
