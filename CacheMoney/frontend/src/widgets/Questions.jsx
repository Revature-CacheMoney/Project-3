import React, { useState } from "react";

function Questions() {
  const [readMore, setReadMore] = useState(false);

  const extraContent = (
    <div className="extra-content-container">
      <ol className="extra-content">
        <h4>Problem Signing in.</h4>
        <p>Attempt the following:</p>
        <li>Input your username and password then click sign in</li>
        <li>
          If you forgot your password or username, email staff and request a
          link to reset them
        </li>
        <li>
          <a href="/test">click here to be redirected to the contact page.</a>
        </li>
      </ol>
      <h4 id="chatbot-faq-create-account-header">
        {" "}
        Problem creating a bank account
      </h4>
      <ol>
        <p>Follow these steps:</p>
        <li>
          If you don't have an account then create an account using the steps
          above in:
          <a href="/test">Trying to create an user account.</a>
        </li>
        <li>
          If you already have an account then login in using your username and
          password
        </li>

        <li> Navigate to the create a bank account page</li>

        <li>Create an account: Checking/Saving or any custom title </li>
        <li>
          <a href="/test">click here to be redirected to the signin page</a>
        </li>
      </ol>
    </div>
  );
  const linkName = readMore ? "Read Less << " : "Read More >> ";
  return (
    <>
      <div className="questions-container">
        <h1>Recently asked questions</h1>
        <ul>
          <h4>Trying to create an user account.</h4>
          <ol>
            <p>Follow these steps: </p>

            <li>Go to sign in page.</li>
            <li>Click registeration Link</li>
            <li>Fill out all fields</li>
            <li>Click the Register Me Button</li>
            <li>
              <a href="/test">
                Click here to be redirected to the registration page
              </a>
            </li>
          </ol>
        </ul>
      </div>
      <div className="read-more-container">
        <button
          className="read-more-link"
          onClick={() => {
            setReadMore(!readMore);
          }}
        >
          <h2>{linkName}</h2>
        </button>
        {readMore && extraContent}
      </div>
    </>
  );
}

export default Questions;
