import React, { useState } from "react";
import userStore from "../store/Store.js";
import config from "../config.js";
import axios from "axios";
import "./Modal.css";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function TOTPModal(props) {
  const navigate = useNavigate();
  const [modal, setModal] = useState(true);

  const toggleModal = () => {
    setModal(!modal);
  };

  if(modal) {
    document.body.classList.add('active-modal')
  } else {
    document.body.classList.remove('active-modal')
  }

  const [formData, setFormData] = useState({
		userId: props.data.userId,
		code: "",
	});
	const handleVerify = () => {
		doVerify();
	};

	const handleChange = (event) => {
		event.preventDefault();
		setFormData({ ...formData, [event.target.name]: event.target.value });
		//
	};

  function doVerify() {
		let responseStatus;
		let responseData;
		let responseHeaders;
		const url = config.url;
		axios.post(`${url}users/verify`, formData)
			.then((response) => {
				responseData = response.data;
				responseStatus = response.status;
				responseHeaders = response.headers;
				if (responseStatus !== 200 || responseData.user_id === null) {
					console.log("Verify failed - bad TOTP TOTP");
					document.getElementById("login-error-box").innerHTML =
						"Error: invalid TOTP code.";
					let loginInputArr = document.getElementsByClassName("login-input");
					for (let i = 0; i < loginInputArr.length; i++) {
						loginInputArr[i].style.border = "2px solid red";
						loginInputArr[i].style.boxShadow = "-4px 4px 0px #b04050";
					}
					//alert("Invalid login attempt.");
				} else {
					console.log("Response body from login API: ", responseHeaders);
											
          userStore.dispatch({
            type: "UPDATE_TOKEN",
            payload: responseHeaders.jwt,
          });
          
          navigate("/main");
					
				}
			})
			.catch((error) => {
        //console.log(error.response);
    		console.error(`Error: ${error}`);
        toast.error(error.response.data, {
          position: "bottom-center",
          autoClose: 2000,
          hideProgressBar: true,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
			});
	}

  return (
    <>
      <ToastContainer
          position="bottom-center"
          autoClose={2000}
          hideProgressBar
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
        />
      {modal && (
        <div className="modal">
          <div onClick={toggleModal} className="overlay"></div>
          <div className="modal-content">
            <h2></h2>
            <span id="login-error-box"></span>
            <label htmlFor="code">TOTP Code:</label>
            <input
              type="text"
              className="login-input"
              name="code"
              id="code"
              onChange={handleChange}
              required
            />
            <button
              className="btn-modal"
              id="verify-button"
              type="submit"
              onClick={handleVerify}
            >
              VERIFY
            </button>
            
          </div>
        </div>
      )}
    </>
  );
}