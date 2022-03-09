import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import config from "../config.js";
import store from "../store/Store.js";

// The registrration component handles the registration form for new users.
// The info is persisted in the database and locally (partial).

function RegisterView() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    username: "",
    password1: "",
    password2: "",
  });

  console.log("username ===>", formData.username);

  const handleChange = (event) => {
    event.preventDefault();
    setFormData({ ...formData, [event.target.name]: event.target.value });
  };

  // handleSubmit: What happens when user presses the "submit" button on reg. form
  const handleSubmit = (event) => {
    event.preventDefault();
    // validate password (do they match?)
    // Validate information
    validateInput(formData);
    console.log("Information being sent:  ", formData);
    doRegistration(formData);
  };

  //   // Checks input against validation (should be same patterns as used in backend)
  function validateInput(info) {
    const { firstName, lastName, email, username, password1, password2 } = info;
    if (password1 !== password2) {
      alert("Passwords do not match!");
      return false;
    }
    // Test the firstname, lastname for validity - ex. no empty strings
    const namePattern = /^[a-zA-Z -]+$/;
    if (!namePattern.test(firstName)) {
      alert("Invalid first name.  Please check the spelling and try again.");
      return false;
    } else if (!namePattern.test(lastName)) {
      alert("Invalid last name.  Please check the spelling and try again.");
      return false;
    }
    const emailPattern =
      /^[a-zA-Z0-9._-]+@{1}[a-zA-Z0-9-_]+[.]{1}[a-zA-Z0-9]+[a-zA-Z_.-]*$/;
    if (!emailPattern.test(email)) {
      alert("Invalid email address.  Please check your input and try again.");
      return false;
    }

    const usernamePattern = /^[a-zA-Z0-9@~._-]{8,}$/;
    if (!usernamePattern.test(username)) {
      alert(
        "Invalid username.  Usernames should be between 8-255 characters in length and use alphanumeric / select symbols.."
      );
      return false;
    }

    const passwordPattern = /^[a-zA-Z0-9@^%$#/\\,;|~._-]{8,50}$/;
    if (!passwordPattern.test(password1)) {
      alert(
        "Invalid password.  Passwords should be between 8-50 characters in length and use alphanumeric / select symbols.."
      );
      return false;
    }
    return true;
  }

  // doRegistration: does the actual registration call
  function doRegistration() {
    let responseStatus;
    // Lets cover any sort of backend response
    let responseData;
    const url = config.url;

    const newUser = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      email: formData.email,
      username: formData.username,
      password: formData.password1,
    };
    axios
      .post(`${url}users/`, newUser)
      .then((response) => {
        console.log(response);
        responseStatus = response.status;
        responseData = response.data;
        if (responseStatus === 200) {
          if (responseData === true || responseData.result === true) {
            //console.log("Registration successful");
            navigate("/signin");
          } else {
            alert(
              "Some error occurred during registration. \n Check if the email or username is already in use?"
            );
          }
        }
      })
      .catch((error) => console.error(`Error: ${error}`));
  }

  return (
    <div className="container-view login-outer-container">
      <div className="login-inner-container">
        <div className="login-content-box">
          <h2 className="logo-smaller" id="register-logo">
            CacheMoney
          </h2>
          <div id="register-white-box" className="login-white-box">
            <div className="login-white-box-column">
              <div id="registration-name-boxes">
                <div id="box-L" className="reg-name-box">
                  <label htmlFor="firstname" id="label-L">
                    First name:
                  </label>
                  <input
                    type="text"
                    name="firstName"
                    id="firstName"
                    title="Enter your first name"
                    placeholder="Enter your first name"
                    className="form-control"
                    onChange={handleChange} // the function we just made! the onChange attribute will automatically pass the `event` argument based off of which input was clicked
                    required
                  />
                </div>

                <div id="box-R" className="reg-name-box">
                  <label htmlFor="lastname" id="label-R">
                    Last name:
                  </label>
                  <input
                    type="text"
                    name="lastName"
                    id="lastName"
                    title="Enter your last name"
                    placeholder="Enter your last name"
                    className="form-control"
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              <div className="reg-field-box">
                <label htmlFor="email">
                  Email:
                  <span className="detail-text">
                    *must be unregistered valid email
                  </span>
                </label>
                <input
                  type="text"
                  name="email"
                  id="email"
                  title="Enter your email"
                  placeholder="Enter your last email"
                  className="form-control"
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="reg-field-box">
                <label htmlFor="username">
                  Username: <span className="detail-text">*must be unique</span>
                </label>
                <input
                  type="text"
                  name="username"
                  id="username"
                  title="Create username: "
                  placeholder="Create username: "
                  className="form-control"
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="reg-field-box">
                <label htmlFor="password">Password:</label>
                <input
                  type="text"
                  name="password1"
                  id="password1"
                  className="password-box"
                  title="Create password"
                  placeholder="Create password"
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="reg-field-box">
                <label htmlFor="password2">Confirm password:</label>
                <input
                  type="text"
                  name="password2"
                  id="password2"
                  className="password-box"
                  title="Confirm password"
                  placeholder="Confirm password"
                  onChange={handleChange}
                  required
                />
              </div>

              <input type="submit" value="Register" onClick={handleSubmit} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RegisterView;

/* Currently disabled (some bug w/ highlighting - needs troubleshooting) */
// This compares the two password entry fields.  If they do not match,
// the boxes are given a red border and the submit button is disabled.
// TODO: Probably want a clearer (text) indicator stating the problem.
// optional: Make the password type "password", give option to flip between visibility
/*
	const checkPasswordEntry = () => {
		const passwordNodes = document.getElementsByClassName("password-box");
		const password1 = passwordNodes[0].value;
		const password2 = passwordNodes[1].value;

		if (password1 === password2) {
			for (let i = 0; i < 2; i++) {
				passwordNodes[i].classList.remove("password-error");
				passwordNodes[i].classList.add("password-ok");
				document.getElementById("submit").disabled = false;
			}
		} else {
			for (let i = 0; i < 2; i++) {
				// the passwords do not match
				passwordNodes[i].classList.remove("password-ok");
				passwordNodes[i].classList.add("password-error");
				document.getElementById("submit").disabled = true;
			}
		}
	}; */
