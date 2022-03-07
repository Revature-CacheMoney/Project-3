import axios from "axios";
import config from "../../config";

function CreateAccount(props) {
    // retrieve the url from the config
    const url = config.url;

    // post account
    const postAccount = (account) => {
        axios.post(`${url}accounts`, account)
            .then((response) => {
                // TODO delete this
                console.log(account);
            })
            .catch(error => console.error(`Error: ${error}`));
    }

    // what the submit button should do
    const handleSubmit = () => {

    }

    return (
        <>
            <div>
                <form>
                    <label htmlFor="name">Account name: </label>
                    <input type="text" id="name" />

                    <input type="radio" id="checking" name="account_type" value="Checking" />
                    <label htmlFor="checking">Checking</label>

                    <input type="radio" id="savings" name="account_type" value="Savings" />
                    <label htmlFor="savings">Savings</label>

                    <button name="submit" onClick={handleSubmit}>Submit</button>
                </form>
            </div>
        </>
    );
}

export default CreateAccount;