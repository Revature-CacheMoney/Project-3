import React from "react";
import { createChatBotMessage } from "react-chatbot-kit";
import Contact from "../widgets/Contact";
import GeneralOptions from "../widgets/Overview";
import AtmLocations from "../widgets/AtmLocations";
import Questions from "../widgets/Questions";
import Information from "../widgets/Information";

/*
The config controls every configurable aspect of the chatbot.

Widgets are custom components that you want to render with a chatbot response.
*/

const botName = "Benjamin";

const chatbotConfig = {
  botName: botName,
  lang: "no",
  customStyles: {
    botMessageBox: {
      backgroundColor: "none",
    },
    chatButton: {
      backgroundColor: "gold",
    },
  },
  initialMessages: [
    createChatBotMessage(`Welcome, `),
    createChatBotMessage(
      "Here's a quick overview of what I can help you with...",
      { widget: "options" }
      //   {
      //     withAvatar: false,
      //     delay: 400,
      //     widget: "overview",
      //   }
    ),
    createChatBotMessage(
      `Or type in " joke " and I will tell you something funny...`
    ),
  ],
  widgets: [
    {
      // defines the name you will use to reference to this widget in "createChatBotMessage".
      widgetName: "options",
      // Function that will be called internally to resolve the widget
      widgetFunc: (props) => <GeneralOptions {...props} />,
      // Any props you want the widget to receive on render
      //props: {},
    },
    {
      widgetName: "Contact",
      widgetFunc: (props) => <Contact {...props} />,
    },
    {
      widgetName: "Atm locations",
      widgetFunc: (props) => <AtmLocations />,
    },
    {
      widgetName: "Recently asked questions",
      widgetFunc: (props) => <Questions />,
    },
    {
      widgetName: "Information",
      widgetFunc: (props) => <Information />,
    },
  ],
};

export default chatbotConfig;

/*
NOTES:

To use your own components in the chatbot, first you need to define it in the "widget" section of the config file:
widgetName: The name to which you will refer to the widget when you call createChatBotMessage
widgetFunc: A function which returns the component you want to render. It needs to take in props and spread
the props out over the given component: (props) => <Component {...props} />
props: An array of props you want to pass to the component
mapStateToProps: An list of properties from configuration state property that you want this component to receive as props.

You will then be able to use the widget when you send a response with createChatBotMessage:
createChatBotMessage("Ok, one moment", { 
  widget: "overview"
})

*/
