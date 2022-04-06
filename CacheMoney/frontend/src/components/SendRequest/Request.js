import React from "react";
import "./Request.css";

const Request = (props) => {
    return (
        <form>
            <label htmlFor="source-id">Source Account</label>
            <input id="source-id" type="number"/>
            
            <label htmlFor="destination-id">Destination Account</label>
            <input id="destination-id" type="number"/>

            <label htmlFor="amount">Amount of Money</label>
            <input id="amount" type="number" min={0.01} step={0.01}/>

            <label htmlFor="description">Memo/Description</label>
            <input id="description" type="text" maxLength={255}/>
        </form>
    );
}

export default Request;