import React, { useState, useEffect } from "react";
import "./AtmLocations.css";

import { atmData } from "../atmData";

function Location() {
  const [atmInfo, setAtmInfo] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getLocations = async () => {
      const atmInfo = await atmData;
      //console.log("atminfo: ", atmInfo);
      setAtmInfo(atmInfo);
      setLoading(false);
    };
    getLocations();
  }, []);

  const listAtms = atmInfo.map((info) => {
    return (
      <div className="atmInfo" key={info.id}>
        <div className="atm-column" loading={loading.toString()}>
          <ul>
            <li>Address: {info.address}</li>
            <li>Zipcode: {info.zipCode}</li>
            <li>State Name: {info.stateName}</li>
            <li>City Name: {info.cityName}</li>
          </ul>
        </div>
      </div>
    );
  });
  return (
    <>
      <h2 id="atm-header" >CacheMoney ATMs</h2>
      <div className="atm_list">{listAtms}</div>
    </>
  );
}

export default Location;
