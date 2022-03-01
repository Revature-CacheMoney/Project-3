import "./css/App.css";
import SplashView from "./components/SplashView.js";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Signin from "./components/SigninView.js";
import Register from "./components/RegisterView.js";
import NotFoundView from "./components/NotFoundView.js";
import MainPageView from "./components/MainPageView";
import AccountsView from "./components/Account/AccountsView";
import Transfer from "./components/TransferView";

function App() {
	return (
		<div>
			<Router>
				<Routes>
					<Route path="/" exact element={<SplashView />} />
					<Route path="/signin" exact element={<SigninView />} />
					<Route path="/register" exact element={<RegisterView />} />
					<Route path="/main" exact element={<MainPageView />} />
					<Route path="/accounts" exact element={<AccountsView />} />
					<Route path="/transfer" exact element={<TransferView />} />
					<Route element={<NotFoundView />} />
				</Routes>
			</Router>
		</div>
	);
}

export default App;
