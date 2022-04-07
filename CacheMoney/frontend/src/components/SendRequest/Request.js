import axios from "axios";
import React, { useState, useEffect } from "react";
import config from "../../config";
import store from "../../store/Store";
import "./Request.css";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer, toast } from "react-toastify";

const Request = (props) => {
    const [accounts, setAccounts] = useState([]);
    const notify=() => {
        toast("")
    }

    //cutting a slice of state to hold form data
    const  [formData, setFormData]  = useState({
        sourceAccountId: 0,
        destinationAccountId: "",
        description: "",
        amount: 0,
    });

    useEffect(() => {
        axios.get(`${config.url}users/accounts`, {
            headers: {
                token: store.getState().userReducer.token,
                userId: store.getState().userReducer.userId,
            }})
        .then((response) => {
            setAccounts(response.data);
            setFormData({...formData, destinationAccountId: response.data[0].accountId})
        })
    },[]);

    const options = accounts.map(account => {
        return (
            <option key={account.accountId} value={account.accountId}>{account.name} (id: {account.accountId})</option>
        );
    });
    
    

    //setting formData using setFormData to update dynamically
    const handleChange = (event) => {
        let value = event.target.value;
        if(event.target.name == "sourceAccountId" || event.target.name == "amount"){
            value = parseInt(value, 10);
        }
        setFormData({...formData, [event.target.name]: value});
    }

    //handles submit request
    const handleSubmit = (submitRequest) => {
        submitRequest.preventDefault();
        const sendingToPost = {
            amount: formData.amount,
            sourceAccount: {accountId: formData.sourceAccountId},
            destinationAccount: {accountId: formData.destinationAccountId},
            description: formData.description
        }
        axios
            .post(`${config.url}request`, sendingToPost, {
                headers: {
                    token: store.getState().userReducer.token,
                    userId: store.getState().userReducer.userId,
                }
            })
            .then((response) => {
                setFormData({
                    sourceAccountId: 0,
                    destinationAccountId: accounts[0],
                    description: "",
                    amount: 0,
                })
                //only notify upon successful 200
                response.status==200?
                toast.success('Request Processed Successfully', {
                    position: "bottom-right",
                    autoClose: 2000,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                }):toast.error('error')
            })
            .catch((error) => {
                console.error(`Error: ${error}`)
                toast.error('Request failed', {
                    position: "bottom-right",
                    autoClose: 2000,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    })
                })
    }
    return (
        <>
        <ToastContainer/>
        <form onSubmit={handleSubmit}>
            <label htmlFor="source-id">Source Account</label>
            <input id="source-id" type="number" min={0} step={1} max={2147483647} name="sourceAccountId" onChange={handleChange}/>

            
            <label htmlFor="destination-id">Destination Account</label>
            <select value={formData.destinationAccountId} id="destination-id" name="destinationAccountId" onChange={handleChange}>
                {accounts.length == 0 && <option value={null}>No Accounts to be Displayed</option>}
                {options}
            </select>

            <label htmlFor="amount">Amount of Money</label>
            <input id="amount" type="number" min={0.01} step={0.01} name="amount" onChange={handleChange}/>

            <label htmlFor="description">Memo/Description</label>
            <input id="description" type="text" maxLength={255} name="description" onChange={handleChange}/>

            <button id="submit-request"> Request Money </button>
        </form>
        </>
    );
}

export default Request;