import axios from "axios";
import React, { useState, useEffect } from "react";
import config from "../../config";
import store from "../../store/Store";
import "./Request.css";

const Request = (props) => {
    const [accounts, setAccounts] = useState([]);

    useEffect(() => {
        axios.get(`${config.url}users/accounts`, {
            headers: {
                token: store.getState().userReducer.token,
                userId: store.getState().userReducer.userId,
            }})
        .then((response) => {
            setAccounts(response.data);
        })
    },[]);

    console.log(accounts);

    const options = accounts.map(account => {
        return (
            <option key={account.accountId} value={account.accountId}>{account.name} (id: {account.accountId})</option>
        );
    });
    return (
        <form>
            <label htmlFor="source-id">Source Account</label>
            <input id="source-id" type="number"/>
            
            <label htmlFor="destination-id">Destination Account</label>
            <select id="destination-id">
                {options}
            </select>

            <label htmlFor="amount">Amount of Money</label>
            <input id="amount" type="number" min={0.01} step={0.01}/>

            <label htmlFor="description">Memo/Description</label>
            <input id="description" type="text" maxLength={255}/>
        </form>
    );
}

export default Request;