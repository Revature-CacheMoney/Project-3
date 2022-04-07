import axios from "axios";
import React, { useState, useEffect } from "react";
import config from "../../config";
import store from "../../store/Store";

const RequestList = (props) => {
    const [requests, setRequests] = useState([]);

    useEffect(() => {
        const gettingSource = axios.get(`${config.url}request/source`,
        {
            headers: {
                token: store.getState().userReducer.token,
                userId: store.getState().userReducer.userId
            }
        })
        const gettingDest = axios.get(`${config.url}request/destination`, {
            headers: {
                token: store.getState().userReducer.token,
                userId: store.getState().userReducer.userId
            }
        })

        const gotten = [];

        Promise.all([gettingSource, gettingDest])
        .then( values => {
            console.log(values);
        })
        .finally(() => {
            console.log(gotten);
        })

    }, []);

    return (
        <h1 style={{color: "black"}}>AAAAAAAAA</h1>
    );
}

export default RequestList;