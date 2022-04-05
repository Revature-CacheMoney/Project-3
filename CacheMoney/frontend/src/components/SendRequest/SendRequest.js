/**
 * @author Shawntaria Burden, Sebastian Fierros
 */
 import axios from "axios";
 import config from "../../config";
 import store from "../../store/Store";
 import { ToastContainer, toast } from 'react-toastify';
 import TransferSelection from "../Transaction/TransferSelection";
 import 'react-toastify/dist/ReactToastify.css';
 
 
 function SendRequest(props) {
     const notify=()=>{
         toast("")
     }

     // post SendRequest transaction
     const postSendRequest = (transfer) => {
         
         axios
             .post(`${config.url}transfer`, transfer, {
                 headers: {
                     token: store.getState().userReducer.token,
                     userId: store.getState().userReducer.userId,
                 },
             })
             .then(
                 res=>{
                 res.status==200?
                     toast.success('SendRequest successful', {
                         position: "bottom-right",
                         autoClose: 2000,
                         hideProgressBar: true,
                         closeOnClick: true,
                         pauseOnHover: true,
                         draggable: true,
                         progress: undefined,
                     }):toast.error('error')
                 
                 }	
                 
                 )
 
             .catch((error) => {
                 console.error(`Error: ${error}`)
                 
                 toast.error('SendRequest failed', {
                         position: "bottom-right",
                         autoClose: 2000,
                         hideProgressBar: true,
                         closeOnClick: true,
                         pauseOnHover: true,
                         draggable: true,
                         progress: undefined,
                         })
                     }
             );
 
             
     };
 
     // what the submit button should do
     const handleSubmit = (event) => {
         // prevent page reloading
         event.preventDefault();
        console.log(store.getState().SendRequestReducer);
         // create the SendRequest payload
         let SendRequest = {
             
             sourceAccountId: store.getState().SendRequestReducer.sourceAccountId,
             destinationAccountId:
                 store.getState().SendRequestReducer.destinationAccountId,
                 description: event.target.Amount.value,
                 Amount: event.target.Amount.value,
         };
 
         // perform the post
         postSendRequest(SendRequest);
         
         
         // hacky workaround to try forcing the accounts list to update
         props.doTransactionDone(Date.now());
     };
 
     return (
         <div className="SendRequest-outer-container">
             <ToastContainer />
             <div className="SendRequest-inner-container">
                 <div className="SendRequest-form">
                     <p className="SendRequest-form-header">SendRequest</p>
 
                     <form id="SendRequest-inner-form" onSubmit={handleSubmit}>
                         <div className="SendRequest-from-account">
                             <label>From</label>
                             <TransferSelection whichAccount="SOURCE"></TransferSelection>
                         </div>
 
                         <div className="SendRequest-to-account">
                             <label>To</label>
                             <TransferSelection whichAccount="DESTINATION"></TransferSelection>
                         </div>
 
                         <div className="SendRequest-amount">
                             <label>Amount</label>
                             <input
                                 type="number"
                                 min="0.00"
                                 step="0.01"
                                 id="SendRequest-input"
                                 name="transactionAmount"
                             />
                         </div>
 
                         <div className="SendRequest-description">
                             <label>Description</label>
                             <input type="text" id="SendRequest-description" name="description" />
                         </div>
 
                         <button type="submit">Submit</button>
                     </form>
                 </div>
             </div>
         </div>
     );
 }
 
 export default SendRequest;
 