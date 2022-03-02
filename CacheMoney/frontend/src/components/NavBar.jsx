import React from "react";

function Navigation() {
  return (
    <>
     <div id="top">
     <div className="nav-bar">
    
        <nav aria-label="breadcrumb">
          <ul className="breadcrumb">
            <li className="breadcrumb-item">
              <a href="/accounts">Accounts</a>
            </li>
            <li className="breadcrumb-item">
              <a href="/accounts/create">Create Account</a>
            </li>
            <li className="breadcrumb-item">
              <a href="/transfer">Transfer Money </a>
            </li>
          </ul>
        </nav>
      </div>
     </div>

    </>
  );
}

export default Navigation;
