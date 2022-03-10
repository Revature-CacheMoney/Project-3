// The NotFound component should be displayed if an invalid URL is entered
import "../css/temp404.css"
import { Link } from "react-router-dom";

// ie. no defined route
function NotFoundView() {
	return (
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
	)
}

export default NotFoundView;
