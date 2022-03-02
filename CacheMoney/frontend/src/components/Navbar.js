import React from "react";

// The NavBar component should allow navigation between different views (after login).
// Ex. Account overview, transfers, create new account, etc.
function NavBar() {
  return (
    <>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="#">Accounts</a>
          </li>
          <li class="breadcrumb-item">
            <a href="#">Create Account</a>
          </li>
          <li class="breadcrumb-item">
            <a href="#">Transfer Money </a>
          </li>
        </ol>
      </nav>
    </>
  );
}

export default NavBar;
