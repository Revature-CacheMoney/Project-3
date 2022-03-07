import axios from "axios";
import { useState } from "react";
import config from "../../config";
import store from "../../store/Store.js";

function CreateAccount(props) {
    const [formData, setFormData] = useState({
        name: "",
        balance: 0,
        type: "",
        userId: {
            // store.getState().userId
            user_id: 1
        }
    })

    // retrieve the url from the config
    const url = config.url;

    // post account
    const postAccount = (account) => {
        axios.post(`${url}accounts`, account)
            .catch(error => console.error(`Error: ${error}`));
    }

    // updates form data when form changes
    const handleChange = (event) => {
        setFormData({ ...formData, [event.target.name]: event.target.value });
    }

    // what the submit button should do
    const handleSubmit = () => {
        postAccount(formData);
    }

    return (
        <>
            <div>
                <form>
                    <label htmlFor="account_name">Account name: </label>
                    <input type="text" id="account_name" name="name" onChange={handleChange} />

                    <input type="radio" id="checking" name="type" value="checking" onChange={handleChange} />
                    <label htmlFor="checking">Checking</label>

                    <input type="radio" id="savings" name="type" value="savings" onChange={handleChange} />
                    <label htmlFor="savings">Savings</label>

                    <button type="button" name="submit" onClick={handleSubmit}>Submit</button>
                </form>
            </div>
        </>
    );
}

export default CreateAccount;