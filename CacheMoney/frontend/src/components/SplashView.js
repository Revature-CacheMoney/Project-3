// The splash screen is the first view the user will see when using the app.
// It prompts the user to login or register.
function SplashView() {
	return (
		<div id="splash-outer-container" className="container-view">
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
	);
}

export default SplashView;
