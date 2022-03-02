import "./css/App.css";
import SplashView from "./components/SplashView.js";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import SigninView from "./components/SigninView.js";
import RegisterView from "./components/RegisterView.js";
import NotFoundView from "./components/NotFoundView.js";
import MainPageView from "./components/MainPageView";
import AccountsView from "./components/Account/AccountsView";
import AccountType from "./components/Account/CreateAccount";
import TransferView from "./components/TransferView";

function App() {
	return (
		<>
			<Router>
				<Routes>
					<Route path="/" exact element={<SplashView />} />
					<Route path="/signin" exact element={<SigninView />} />
					<Route path="/register" exact element={<RegisterView />} />
					<Route path="/main" exact element={<MainPageView />} />
					<Route path="/accounts" exact element={<AccountsView />} />
					<Route path="/accounts/create" exact element={<AccountType />} />
					<Route path="/transfer" exact element={<TransferView />} />
					<Route element={<NotFoundView />} />
				</Routes>
			</Router>
		</>
	);
}

export default App;
