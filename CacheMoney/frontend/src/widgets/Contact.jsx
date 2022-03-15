import React from "react";
import ContactEntry from "./ContactEntry";
import { DevData } from "./../DevelopersData";

function Contact(props) {
  return (
    <>
      <div className="info-list-container">
        <ul>
        
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
