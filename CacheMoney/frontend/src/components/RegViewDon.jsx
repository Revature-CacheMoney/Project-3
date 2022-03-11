import React, { useState } from "react";
import { useHistory } from "react-router-dom";

export default function ViewTwo() {
  const history = useHistory();
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    username: "",
    password: "",
  });

  function handleChange({ target }) {
    setFormData({ ...formData, [target.name]: target.value });
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    history.push("/signin"); //this NEEDS an api call
    //example from other project not sure how to make this happen with ours though
    //history.push(`/dashboard?date=${formData.reservation_date}`); // the push function literally "pushes" the user to whatever path you give.
  };
  return (
    <main>
      <div className="nav-bar">
        <h1>NavBAr here</h1>
      </div>
      <div className="header">
        <h2 className="logo-smaller" id="register-logo">
          CacheMoney
        </h2>
      </div>
      <div className="form">
        <form>
          <fieldset>
            <legend>Customer Information:</legend>
            <div className="name_info">
              <div className="form-group">
                <label htmlFor="firstName">First Name:&nbsp;</label>
                <input
                  name="firstName"
                  id="firstName"
                  type="text"
                  title="Enter your first name"
                  placeholder="Enter your first name"
                  className="form-control"
                  onChange={handleChange} // the function we just made! the onChange attribute will automatically pass the `event` argument based off of which input was clicked
                  value={formData.firstName} // we can use our useState hook to store the values of each input now
                  required
                ></input>
              </div>
              <div className="form-group">
                <label htmlFor="lastName">Last Name:&nbsp;</label>
                <input
                  name="lastName"
                  id="lastName"
                  type="text"
                  title="Enter your last name"
                  placeholder="Enter your last name"
                  className="form-control"
                  onChange={handleChange}
                  value={formData.lastName}
                  required
                ></input>
              </div>
              <div className="form-group">
                <label htmlFor="email">Email:&nbsp;</label>
                <input
                  name="email"
                  id="email"
                  type="email"
                  title="Enter your email: "
                  placeholder="Enter your email: "
                  className="form-control"
                  onChange={handleChange}
                  value={formData.email}
                  required
                ></input>
              </div>
            </div>
            <div className="party_info">
              <div className="form-group">
                <label htmlFor="username">Username:&nbsp;</label>
                <input
                  name="username"
                  id="username"
                  type="username"
                  className="form-control"
                  onChange={handleChange}
                  value={formData.username}
                  required
                ></input>
              </div>
              <div className="form-group">
                <label htmlFor="password">Password:&nbsp;</label>
                <input
                  name="password"
                  id="password"
                  type="security"
                  className="form-control"
                  onChange={handleChange}
                  value={formData.password}
                  required
                ></input>
              </div>
            </div>
          </fieldset>
        </form>
      </div>
    </main>
  );
}
