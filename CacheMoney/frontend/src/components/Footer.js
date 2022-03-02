import React from "react";
import footerLogo from "../image/logofooter.png";

function Footer(props) {
	return (
		<div className="row">
			<div className="column">
				<h2>Disclosures</h2>
				<p>
					CacheMoney LLC and its affiliates offer banking products which are not
					insured by the FDIC or any other government agency. Deposit or other
					obligations are subject to investment risks. Possible loss of the
					principal amount deposited.
				</p>
			</div>
			<div className="column">
				<h2>Customer Service</h2>
				<li>
					<p>Front Desk</p>
				</li>
				<li>
					<p>Development Team</p>
				</li>
				<li>
					<p>Request a New Card</p>
				</li>
				<li>
					<p>Report Fradulent Activity</p>
				</li>
				<li>
					<p>Order a Hotdog Pizza</p>
				</li>
			</div>
			<div className="column">
				<h2>Partners</h2>
				<li>
					<p>High Risk Moderate Reward Investing</p>
				</li>
				<li>
					<p>Blue Sky Inc.</p>
				</li>
				<li>
					<p>Illuminati Initiative</p>
				</li>
				<li>
					<p>Global Domination Timeline</p>
				</li>
				<li>
					<p>HotdogPizza Sandwich Association</p>
				</li>
			</div>
			<div className="column">
				<h2></h2>
				<img src={footerLogo} />
			</div>
		</div>
	);
}
export default Footer;
