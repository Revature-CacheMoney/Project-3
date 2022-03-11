// @author: Associate Ahmad Rawashdeh
// @description: the chat support questions and answers
// ______________________________________________ DATA NEEDED _____________________________

//_____________________________________________________________________________________________________
//NOTE: below is the description of the different states and transitions including prompts and options
//______________________________________________________________________________________________________
// > state 1:
//    'how can I help you'
//        options (MainMenu): 
//               registration (2), sign in (3),  transaction (4), talk to agent (5), quit (9)

                          //  >> state 2: 
                          //    'pick one of the following'
                          //        options: link to registration page (6), talk to agent (5)

                          //      >>>>>> state 6:
                          //               {give LINK}: link to registration page is given
                          //               option: Main Menu (1)
                          //                      {move to state 1}

                          // >> state 3:
                          //    'pick one of the following'
                          //        option: link to sign in (7), talk to agent (5)
                                  
                          //      >>>>>> state 7:
                          //               {give LINK}: link to sign in page is given
                          //                 option: Main Menu (1)
                          //                       {move to state 1}
                          // >> state 4:
                          //    'pick one of the following'
                          //        option: link to transaction (8), talk to agent (5)

                          //       >>>>>> state 8:
                          //              {give LINK}: link to transaction page (require login) is given
                          //                 option: Main Menu (1)
                          //                       {move to state 1}

                          // >> state 5: talk to agent
                          // >> state 9: quit

//____________________________ java script code ________________________________
var options = ['root',   '1: Main Menu', '2: Registration', 
//                0           1           2
               '3: Sign in', '4: Transaction', '5: Talk to Agent', 
//                 3                  4            5
                '6: Link to registration', '7: Link to sign in', '8: Link to Transaction', '9: Quit'];
//                         6                       7                     8                     9

var links = {registrationLink: 'testing\\registration.html', 
               signinLink: 'testing\\signing.html', 
               transactionLink: 'testing\\transaction.html'};
//                0                                     1                          2

//clear chat content
function clear()
{
    var output = document.getElementById('outputLabel');
    output.innerHTML = "";

}

//modify this method if message needs to be displayed in the chat window or by any other mean and not to the console. 
//create an option button
function PrintButtons(str, state) //display the option as a button
{
    var output = document.getElementById('outputLabel');
    console.log("<button onclick=\"getSupport(\"" + state + "\")\">" + str + "</button>");
    output.innerHTML += "<button onclick=\"getSupport('" + state + "')\">" + str + "</button>";

}
//display message
function PrintText(str) //print the message as response
{
    var output = document.getElementById('outputLabel'); 
    output.innerHTML +=  "<p>" + str + "</p>";
}

//print HTML
function PrintHTML(str)
{
    var output = document.getElementById('outputLabel');  
    output.innerHTML += str;
}

//invoked one the start chat button is clicked (only invoked once)
function initialSupportMessage()
{
    //start the support chat by invoking getSupport with 1 as the initial state (that will display the first options)
    //hide the "start chat button"
    document.getElementById('startButton').style.visibility = 'hidden';
    //start the support dialog by asking questions and initalize state to 1
    getSupport("1");
}

//a function which display chat option to the user depending on his previous answers
function getSupport(input)
{
    switch(input)
    {
        case "1": //state 1
            {
                
                clear(); //clear the output
                PrintText('How can I help you?');
                PrintButtons(options[2], 2);
                PrintButtons(options[3], 3);
                PrintButtons(options[4], 4);
                PrintButtons(options[5], 5);
                PrintButtons(options[9], 9);
                break;
            }
        case "2": //state 2
                {
                    
                    clear(); //clear the output
                    PrintText('pick one of the following');
                    PrintButtons(options[6], 6);
                    PrintButtons(options[5], 5);
                    break
                }
        case "3": //state 3
                {
                    clear(); //clear the output
                    PrintText('pick one of the following');
                    PrintButtons(options[7], 7);
                    PrintButtons(options[5], 5);
                    break
                }
        case "4":
                {
                    
                    clear(); //clear the output
                    PrintText('pick one of the following');
                    PrintButtons(options[8], 8);
                    PrintButtons(options[5], 5);
                    break
                }
        case "5":
                {
                    
                    clear(); //clear the output
                    PrintText('connecting you with an agent. just a moment');
                    //agent is now interacting with the customer
                    break;
                }
        case "6":
                {
                    //registration link
                    clear(); //clear the output
                    console.log("<a hre=\"" + links.registrationLink + "\">Registration Link</a></br></br>");
                    PrintHTML("<a hre=\"" + links.registrationLink + "\">Registration Link</a></br></br>");
                    PrintButtons(options[1], 1);
                    break;
                }
        case "7":
                {
                    //signing in link
                    clear(); //clear the output
                    PrintHTML("<a hre=\"" + links.signinLink + "\">signing Link</a></br></br>");
                    PrintButtons(options[1], 1);
                    break;
                }
        case "8":
                {
                    //transaction link
                    
                    clear(); //clear the output
                    PrintHTML("<a hre=\"" + links.transactionLink + "\">Transaction Link</a></br></br>");
                    PrintButtons(options[1], 1);
                    break;
                }
        case "9":
                 {
                    
                    clear(); //clear the output
                    //save history of chat in log
                    PrintText('quitting chat');
                     return;
                 }
        default:{
            
            clear(); //clear the output
            PrintText('invalid option');
            }
    }
    event.preventDefault(); //ignore post back to server
}