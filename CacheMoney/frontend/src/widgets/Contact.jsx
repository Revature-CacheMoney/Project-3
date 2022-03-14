import React, { useState } from "react";
import ContactEntry from "./ContactEntry";
import { DevData } from "./../DevelopersData";

function Contact(props) {
  // let info = DevData.map((dd) => {
  //   return dd;
  // });
  return (
    <>
      <div className="info-list-container">
        <ul>
          {/* <ContactEntry name={props.name} github={props.github} /> */}
          {DevData.map((dd) => {
            return (
              <li key={dd.id}>
                <ContactEntry name={dd.name} github={dd.github} />
              </li>
            );
          })}
        </ul>
      </div>
    </>
  );
}

export default Contact;
