/**
 * @author Ibrahima Diallo, Brian Gardner, Cody Gonsowski, & Jeffrey Lor
 */

import axios from "axios";
import { useState } from "react";
import config from "../../config";
import store from "../../store/Store.js";

function CreateAccount(props) {
  // local formData state
  const [formData, setFormData] = useState({
    name: "",
    balance: 0,
    type: "",
    user: {
      userId: store.getState().userReducer.userId,
    },
  });

  // retrieve the url from the config
  const url = config.url;

  // post account
  const postAccount = (account) => {
    axios
      .post(`${url}accounts`, account, {
        headers: {
          token: store.getState().userReducer.token,
          userId: store.getState().userReducer.userId,
        },
      })
      .catch((error) => console.error(`Error: ${error}`));
  };

  // updates form data when form changes
  const handleChange = (event) => {
    event.preventDefault();
    setFormData({ ...formData, [event.target.name]: event.target.value });
  };

  // what the submit button should do
  const handleSubmit = () => {
    postAccount(formData).confirm("Press a button!\nEither OK or Cancel.");
    // alert(
    //   "Account successfully created please return to the accounts page to start using your new account."
    // );
  };

  return (
    <div className="create-account-outer-container">
      <div className="create-account-inner-container">
        <div className="account_create_form">
          <p className="account_create_form_header">Create Account</p>

          <form>
            <div className="account_create_name">
              <label htmlFor="account_name">Account Name</label>
              <input
                type="text"
                id="account_name"
                name="name"
                onChange={handleChange}
              />
            </div>

            <div className="account_create_radio_button_group">
              <p className="account_create_type_header">Account Type</p>

              <div>
                <div className="account_create_radio_button">
                  <input
                    type="radio"
                    id="checking"
                    name="type"
                    value="checking"
                    onChange={handleChange}
                  />
                  <label htmlFor="checking">Checking</label>
                </div>

                <div className="account_create_radio_button">
                  <input
                    type="radio"
                    id="savings"
                    name="type"
                    value="savings"
                    onChange={handleChange}
                  />
                  <label htmlFor="savings">Savings</label>
                </div>
              </div>
            </div>
          </form>

          <button
            className="account_create_submit_button"
            type="button"
            name="submit"
            onClick={handleSubmit}
          >
            Submit
          </button>
        </div>
      </div>
    </div>
  );
}

export default CreateAccount;
