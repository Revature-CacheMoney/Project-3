/**
 * @author Shawntaria Burden, Sebastian Fierros, Ethan Edmond, Tyler Daniel
 */

import React from "react";
import Request from "./Request";
import RequestList from "./RequestList";
import "./RequestTab.css";

const RequestTab = (props) => {
    return (
        <div className="RequestTab">
            <Request/>
            <RequestList/>
        </div>
    );
}

export default RequestTab;