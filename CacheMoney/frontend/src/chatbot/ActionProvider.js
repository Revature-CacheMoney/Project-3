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
      "What can I assist you with today?"
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

  handleInformation = () => {
    const message = this.createChatBotMessage("Here is a link to our readMe on github for our project: \n", {
      widget: "Information",
      loading: true,
      terminateLoading: true,
    });
    this.addMessageToState(message);
  }
  handleJoke = () => {
    var jokes = [
      "I'm afraid for the calendar. Its days are numbered.",
      "My wife said I should do lunges to stay in shape. That would be a big step forward.",
      "Why do fathers take an extra pair of socks when they go golfing? In case they get a hole in one!",
      "Singing in the shower is fun until you get soap in your mouth. Then it's a soap opera.",
      "What do a tick and the Eiffel Tower have in common? They're both Paris sites.",
      "What do you call a fish wearing a bowtie? Sofishticated.",
      "How do you follow Will Smith in the snow? You follow the fresh prints.",
      "If April showers bring May flowers, what do May flowers bring? Pilgrims.",
    ];

    var randomJoke = jokes[Math.floor(Math.random() * jokes.length)];

    const message = this.createChatBotMessage(randomJoke);

    this.addMessageToState(message);
  };
  handleThanks = () => {
    const message = this.createChatBotMessage("Stay 100!");

    this.addMessageToState(message);
  };
}

export default ActionProvider;
