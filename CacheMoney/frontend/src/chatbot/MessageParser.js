/*

The message parser controls how the bot reads input and decides which action to invoke.

Once passed the messageparser to the chatbot, 
it will initialize it with the actionprovider you provided, 
so that you can determine which action after you have parsed the message.

*/

class MessageParser {
  constructor(actionProvider, state) {
    this.actionProvider = actionProvider;
    // console.log(actionProvider);
    // console.log(state);
    this.state = state;
  }

  parse(message) {
    console.log(message);
    const input = message.toLowerCase();

    if (input.includes("hello", "HELLO", "Hello")) {
      return this.actionProvider.greet();
    }
    if (input.includes("overview")) {
      return this.actionProvider.handleOptions();
    }
    if (input.includes("contact")) {
      return this.actionProvider.handleContactInfo();
    }
    if (input.includes("atm")) {
      return this.actionProvider.handleAtmFinder();
    }
    if (input.includes("questions")) {
      return this.actionProvider.handleAtmFinder();
    }
    if (input.includes("info", "information")) {
      return this.actionProvider.handleInformation();
    }
    if (
      input.includes("joke") ||
      input.includes("jokes") ||
      input.includes("funny")
    ) {
      return this.actionProvider.handleJoke();
    }

    if (input.includes("thanks") || input.includes("thank you")) {
      return this.actionProvider.handleThanks();
    }

  }
}

export default MessageParser;
