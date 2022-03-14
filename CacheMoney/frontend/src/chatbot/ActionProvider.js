/*

The action provider defines the bots response after the message is parsed.
Contain functions that you want to call in response to a user input. 
The actionprovider is given to the messageparser so you can use any methods you provide here inside the messageparser.

The bots response is controlled by the createChatBotMessage function that is given to the actionprovider:
*/

class ActionProvider {
    constructor(createChatBotMessage, setStateFunc) {
      this.createChatBotMessage = createChatBotMessage;
      //console.log(setStateFunc);
      //console.log(createChatBotMessage);
      this.setState = setStateFunc;
    }
  
    greet = () => {
      const message = this.createChatBotMessage(
        "What can I assit you with today?"
      ); //take this message object and update the chatBot state
      this.addMessageToState(message);
    };
  
    //to do this we use the setState function
    //update the chatbot state
    addMessageToState = (message) => {
      //we always have the state in this funciton
      //So we need a way to preserve the prevState when we are updating
      this.setState((prevState) => ({
        ...prevState,
        messages: [...prevState.messages, message], //copy the prevState messages over and add the new message at the end
      }));
    };
  
    handleOptions = (options) => {
      const message = this.createChatBotMessage(
        //"Please do something here for me.",
        {
          widget: "overview",
          loading: true,
          terminateLoading: true,
          ...options,
        }
      );
      this.addMessageToState(message);
    };
  
    handleContactInfo = () => {
      const message = this.createChatBotMessage(
        "Here is a list of our top team members: \n",
        {
          widget: "Contact",
          loading: true,
          terminateLoading: true,
          // withAvatar: true,
        }
      );
      this.addMessageToState(message);
    };
  
    handleAtmFinder = () => {
      const message = this.createChatBotMessage(
        "Here is a list of locations for atms: \n",
        {
          widget: "Atm locations",
          loading: true,
          terminateLoading: true,
          // withAvatar: true,
        }
      );
      this.addMessageToState(message);
    };
  
    handleQuestions = () => {
      const message = this.createChatBotMessage("Here is a list questions: \n", {
        widget: "Recently asked questions",
        loading: true,
        terminateLoading: true,
      });
      this.addMessageToState(message);
    };
  }
  
  export default ActionProvider;
  