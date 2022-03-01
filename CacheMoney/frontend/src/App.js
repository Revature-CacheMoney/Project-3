import "./css/App.css";
import Splash from "./components/Splash.js";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Signin from "./components/Signin.js";
import Register from "./components/Register.js";
import NotFound from "./components/NotFound.js";
import MainPage from "./components/MainPage";
import AccountsView from "./components/Account/AccountsView";
import Transfer from "./components/Transfer";


function App() {
	return (
		<div>
			<Router>
				<Routes>
					<Route path="/" exact element={<Splash />} />
					<Route path="/signin" exact element={<Signin />} />
					<Route path="/register" exact element={<Register />} />
					<Route path ="/main" exact element={<MainPage/>} />
					<Route path ="/accounts" exact element={<AccountsView/>} />
					<Route path ="/transfer" exact element={<Transfer/>} />
					<Route element={<NotFound />} />
				</Routes>
			</Router>
		</div>
	);
}

export default App;
